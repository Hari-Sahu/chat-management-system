package com.example.cms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.ChatGroup;
import com.example.cms.dao.entities.GroupUser;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ChatGroupRepository;
import com.example.cms.dao.repositiries.GroupUserRepository;
import com.example.cms.dto.requests.ChatGroupRequest;
import com.example.cms.supports.enums.GroupUserRole;
import com.example.cms.supports.enums.SendMessagePermission;

import jakarta.transaction.Transactional;

@Service
public class ChatGroupService {
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private UserService userServ;
	
	@Autowired
    private ChatGroupRepository chatGrpRepo;
	
	@Autowired
    private GroupUserRepository grpUserRepo;
	
	@Autowired
    private ChatMemberService chatMemberService;
	
	@Transactional
	public String createGroup(User user, ChatGroupRequest reqDTO) {
		List<User> grpMembers = new ArrayList<>();
		reqDTO.getMobileNumbers().forEach(number -> {
			if(!number.equals(user.getMobile())) {
				grpMembers.add(userServ.getUserByMobileNumber(number));
			}
		});
		
		Chat chat = chatService.createChat(true);
		ChatGroup chatGrp = new ChatGroup(chat, reqDTO.getName());
		chatGrp.setSendMessagePermission(SendMessagePermission.ALL_USERS);
		chatGrp.setGroupImageUrl(reqDTO.getGroupImageUrl());
		chatGrpRepo.save(chatGrp);
		
		addUserInGroup(chatGrp, user, GroupUserRole.ADMIN);
		chatMemberService.createChatMember(chat, user, null);
		
		grpMembers.forEach(usrM -> {
			addUserInGroup(chatGrp, usrM, GroupUserRole.NON_ADMIN);
			chatMemberService.createChatMember(chat, usrM, null);
		});
		
		return "Chat group created successfully";
	}
	
	public void addUserInGroup(ChatGroup chatGrp, User user, GroupUserRole grpUsrRole) {
		GroupUser grpUsr = new GroupUser(chatGrp, user);
		grpUsr.setUserRole(grpUsrRole);
		grpUserRepo.save(grpUsr);
	}    
}
