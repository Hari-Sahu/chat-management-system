package com.example.cms.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class ChatGroupRequest {
	
	@NotBlank(message = "Group name must not be blank")
    private String name;

    private String groupImageUrl;
    
    @NotEmpty(message = "At least two mobile number is required")
    private List<@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String> mobileNumbers;

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

	public List<String> getMobileNumbers() {
		return mobileNumbers;
	}

	public void setMobileNumbers(List<String> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
}
