package com.example.cms.dao.entities;

import com.example.cms.supports.conveters.SendMessagePermissionConverter;
import com.example.cms.supports.enums.SendMessagePermission;
import com.example.cms.utils.EntityIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_groups")
public class ChatGroup extends BaseEntity {

	@Column(nullable = false)
    private String name;

	@Column(name = "group_image_url")
    private String groupImageUrl;

	@Column(name = "send_message_permission")
	@Convert(converter = SendMessagePermissionConverter.class)
	private SendMessagePermission sendMessagePermission;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false)
    private Chat chat;
	
	public ChatGroup() {
        this.id = EntityIdGenerator.generateId();
    }

	public ChatGroup(Chat chat, String name) {
		super();
		this.id = EntityIdGenerator.generateId();
		this.chat = chat;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupImageUrl() {
		return groupImageUrl;
	}

	public void setGroupImageUrl(String groupImageUrl) {
		this.groupImageUrl = groupImageUrl;
	}

	public SendMessagePermission getSendMessagePermission() {
		return sendMessagePermission;
	}

	public void setSendMessagePermission(SendMessagePermission sendMessagePermission) {
		this.sendMessagePermission = sendMessagePermission;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
}
