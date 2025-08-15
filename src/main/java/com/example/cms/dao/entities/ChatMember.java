package com.example.cms.dao.entities;

import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_members")
public class ChatMember extends BaseEntity {
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "display_name")
    private String displayName;
    
    @Column(name = "chat_read", nullable = false)
    private boolean chatRead;

    public ChatMember() {
		super();
		this.id = EntityIdGenerator.generateId();
	}

	// Getters and Setters
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isChatRead() {
		return chatRead;
	}

	public void setChatRead(boolean chatRead) {
		this.chatRead = chatRead;
	}
}
