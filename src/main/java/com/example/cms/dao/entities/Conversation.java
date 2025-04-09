package com.example.cms.dao.entities;

import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversations")
public class Conversation extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", nullable = false)
    private User sender;
    
    public Conversation() {
		super();
		this.id = EntityIdGenerator.generateId();
	}

	public Conversation(Chat chat, String message, User sender) {
		super();
		this.id = EntityIdGenerator.generateId();
		this.chat = chat;
		this.message = message;
		this.sender = sender;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}
