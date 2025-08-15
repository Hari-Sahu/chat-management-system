package com.example.cms.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ChatRepository;
import com.example.cms.dto.responses.ChatDetailsObject;
import com.example.cms.exceptions.ServiceErrorCodes;
import com.example.cms.exceptions.ServiceException;

import jakarta.transaction.Transactional;

@Service
public class ChatService {
	
	@Autowired
	private UserService userServ;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMemberService chatMemberService;

    @Transactional
    public ChatDetailsObject initiateChat(User currentUser, String otherUserMobile) {
        if(currentUser.getMobile().equals(otherUserMobile)) {
        	throw new ServiceException(ServiceErrorCodes.INVALID_INITIATE_CHAT);
        }
        
        User otherUser = userServ.getUserByMobileNumber(otherUserMobile);
        boolean isChatExist = chatMemberService.isChatExist(currentUser, otherUser);
        if(isChatExist) {
        	throw new ServiceException(ServiceErrorCodes.CHAT_ALREADY_EXIST);
        }
        
        Chat chat = createChat(false);
        chatMemberService.createChatMember(chat, currentUser, otherUser.getName());
        chatMemberService.createChatMember(chat, otherUser, currentUser.getName());
        
        return mapToDTO(chat, otherUser);
    }
    
    // Create chat
    public Chat createChat(boolean isGroup) {
        Chat chat = new Chat(isGroup);
        return chatRepository.save(chat);
    }
    
    public Chat getChat(String id) {
        return chatRepository.findById(id)
        		.orElseThrow(() -> new ServiceException(ServiceErrorCodes.DATA_NOT_FOUND, "Chat"));
    }
    
    public void updateChatTime(Chat chat) {
    	chat.setUpdatedOn(new Date());
    	chatRepository.save(chat);
    }
    
    private ChatDetailsObject mapToDTO(Chat chat, User user) {
    	ChatDetailsObject dto = new ChatDetailsObject();
        dto.setId(chat.getId());
        dto.setGroup(chat.isGroup());
        if(chat.isGroup()) {
        	dto.setDisplayName(null);
        	dto.setGroupId(null);
        } else {
        	dto.setDisplayName(user.getName());
        	dto.setSendMessagePermission(true);
        }
        return dto;
    }
}
