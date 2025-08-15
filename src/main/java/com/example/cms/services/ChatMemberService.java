package com.example.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.ChatMember;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ChatMemberRepository;

@Service
public class ChatMemberService {
	
    @Autowired
    private ChatMemberRepository chatMemberRepository;

    public void createChatMember(Chat chat, User user, String displayName) {
    	 ChatMember member = new ChatMember();
         member.setChat(chat);
         member.setUser(user);
         member.setDisplayName(displayName);
         member.setChatRead(true);
         
         chatMemberRepository.save(member);
    }
    
    public boolean isChatExist(User currentUser, User otherUser) {
    	return chatMemberRepository.isChatExist(currentUser, otherUser);
    }
}
