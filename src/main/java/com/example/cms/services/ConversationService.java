package com.example.cms.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.Conversation;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ConversationRepository;
import com.example.cms.dto.responses.ConversationResponseObject;

import jakarta.transaction.Transactional;

@Service
public class ConversationService {
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
    private ConversationRepository converRepo;
	
	@Transactional
	public ConversationResponseObject sendMessage(User user, String chatId, String message) {
		Chat chat = chatService.getChat(chatId);
		Conversation conversation = new Conversation(chat, message, user);
		converRepo.save(conversation);
		return mapToDTO(conversation);
	}
    
    private ConversationResponseObject mapToDTO(Conversation conver) {
    	ConversationResponseObject dto = new ConversationResponseObject();
        dto.setId(conver.getId());
        dto.setMessage(conver.getMessage());
        dto.setSenderName(conver.getSender().getName());
        
        // Convert to Instant
        Instant instant;
        if(conver.getCreatedOn() == null)
        	instant = new Date().toInstant();
        else
        	instant = conver.getCreatedOn().toInstant();

        // Convert to LocalDate and LocalTime (system default zone, or specify one)
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        dto.setCreatedAtDate(localDate);
        
        LocalTime localTime = instant.atZone(ZoneId.systemDefault()).toLocalTime();
        dto.setCreatedAtTime(localTime);
        
        return dto;
    }
}
