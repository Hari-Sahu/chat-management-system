package com.example.cms.dto.responses;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ConversationResponseObject {

	private String id;
	private String message;
	private MessageSenderObject senderName;
	
	@JsonFormat(pattern = "dd MMM yyyy")
    private LocalDate createdAtDate;

    @JsonFormat(pattern = "hh:mm a")
    private LocalTime createdAtTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageSenderObject getSender() {
		return senderName;
	}
	public void setSender(MessageSenderObject sender) {
		this.senderName = sender;
	}
	public LocalDate getCreatedAtDate() {
		return createdAtDate;
	}
	public void setCreatedAtDate(LocalDate createdAtDate) {
		this.createdAtDate = createdAtDate;
	}
	public LocalTime getCreatedAtTime() {
		return createdAtTime;
	}
	public void setCreatedAtTime(LocalTime createdAtTime) {
		this.createdAtTime = createdAtTime;
	}
}
