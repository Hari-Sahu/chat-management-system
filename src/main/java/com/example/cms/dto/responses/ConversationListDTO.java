package com.example.cms.dto.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ConversationListDTO extends AppResponse {

	private Long etag;
	private List<ConversationResponseObject> conversations = new ArrayList<>();

	public Long getEtag() {
		return etag;
	}

	public void setEtag(Long etag) {
		this.etag = etag;
	}

	public List<ConversationResponseObject> getConversations() {
		return conversations;
	}

	public void setConversations(List<ConversationResponseObject> conversations) {
		this.conversations = conversations;
	}
	
	public void addConversation(ConversationResponseObject obj) {
		conversations.add(obj);
	}
}
