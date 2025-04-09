package com.example.cms.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.Conversation;
import com.example.cms.dao.entities.User;
import com.example.cms.dao.repositiries.ConversationRepository;
import com.example.cms.dto.responses.ConversationListDTO;
import com.example.cms.dto.responses.ConversationResponseObject;
import com.example.cms.dto.responses.MessageSenderObject;

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
		chatService.updateChatTime(chat); //Update the chat's updated_at timestamp to reflect the most recent activity in the list
		return mapToDTO(conversation);
	}
	
	public ConversationListDTO getChatMessages(String chatId) {
		Chat chat = chatService.getChat(chatId);
		List<Conversation> conversationList = converRepo.findByChatOrderByCreatedOnDesc(chat);
		ConversationListDTO res = new ConversationListDTO();
		conversationList.forEach(conver -> res.addConversation(mapToDTO(conver)));
		return res;
	}
    
    private ConversationResponseObject mapToDTO(Conversation conver) {
    	ConversationResponseObject dto = new ConversationResponseObject();
        dto.setId(conver.getId());
        dto.setMessage(conver.getMessage());
        MessageSenderObject sender = new MessageSenderObject();
        sender.setId(conver.getSender().getId());
        sender.setName(conver.getSender().getName());
        dto.setSender(sender);
        
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
