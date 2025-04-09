package com.example.cms.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ChatDetailsObject {

	private String id;
	private boolean isGroup;
	private String displayName;
	private String groupId;
	private boolean sendMessagePermission;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public boolean isSendMessagePermission() {
		return sendMessagePermission;
	}
	public void setSendMessagePermission(boolean sendMessagePermission) {
		this.sendMessagePermission = sendMessagePermission;
	}
}
