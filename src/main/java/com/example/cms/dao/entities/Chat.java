package com.example.cms.dao.entities;

import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

	@Column(name = "is_group", nullable = false)
    private boolean isGroup;

	public Chat() {
		super();
		this.id = EntityIdGenerator.generateId();
	}
	
	public Chat(boolean isGroup) {
		super();
		this.id = EntityIdGenerator.generateId();
		this.isGroup = isGroup;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
}
