package com.example.cms.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class ConversationRequest {
	
	@NotBlank(message = "Message is required")
    private String message;

	public String getMessage() {
		return message;
	}
}
