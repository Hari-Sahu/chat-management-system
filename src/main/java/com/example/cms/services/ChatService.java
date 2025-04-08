package com.example.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.ChatMember;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ChatMemberRepository;
import com.example.cms.dao.repositiries.ChatRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatService {
	
	@Autowired
	private UserService userServ;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMemberRepository chatMemberRepository;

    @Transactional
    public void initiateChat(User currentUser, String otherUserMobile) {
        User otherUser = userServ.getUserByMobileNumber(otherUserMobile);

        // Create chat
        Chat chat = new Chat(false);
        chat = chatRepository.save(chat);

        // Add both users to chat_members
        ChatMember member1 = new ChatMember();
        member1.setChat(chat);
        member1.setUser(currentUser);

        ChatMember member2 = new ChatMember();
        member2.setChat(chat);
        member2.setUser(otherUser);

        chatMemberRepository.save(member1);
        chatMemberRepository.save(member2);
    }
}
