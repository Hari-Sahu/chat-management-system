package com.example.cms.dto.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ChatListDTO extends AppResponse {

	private List<ChatDetailsObject> chats = new ArrayList<>();
	
	public List<ChatDetailsObject> getChats() {
		return chats;
	}

	public void setChats(List<ChatDetailsObject> chats) {
		this.chats = chats;
	}

	public void addChat(ChatDetailsObject obj) {
		chats.add(obj);
	}
}
