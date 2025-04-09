package com.example.cms.dao.repositiries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.dao.entities.Chat;
import com.example.cms.dao.entities.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
	
	List<Conversation> findByChatOrderByCreatedOnDesc(Chat chat);
}
