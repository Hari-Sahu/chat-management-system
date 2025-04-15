package com.example.cms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.configs.interceptor.AuthenticationRequired;
import com.example.cms.dao.entities.User;
import com.example.cms.dto.requests.ChatGroupRequest;
import com.example.cms.exceptions.AppErrorCodes;
import com.example.cms.exceptions.AppException;
import com.example.cms.services.ChatGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/chats/groups")
@Tag(name = "Chat Groups", description = "APIs for creating and managing chat groups")
public class ChatGroupController extends TokenAuthenticationController {

    @Autowired
    private ChatGroupService chatGrpService;
    
	@Operation(summary = "Create a chat group",
			description = "Creates a new chat group with the specified name, group image URL, and a list of mobile numbers.",
			parameters = {
					@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
					@Parameter(name = "Authorization", description = "Bearer token", required = true)
			})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Chat group created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data")
	})
	@PostMapping
	@AuthenticationRequired
	public ResponseEntity<String> createChatGroup(@RequestBody @Valid ChatGroupRequest reqDTO) {
		User loggedInUser = getUserAccount();
		validateGroupUsers(loggedInUser, reqDTO.getMobileNumbers());
		return ResponseEntity.ok(chatGrpService.createGroup(loggedInUser, reqDTO));
	}
	
	private void validateGroupUsers(User loggedInUser, List<String> mobileNumbers) {		
		List<String> formattedMob = mobileNumbers.stream().filter(mob -> !mob.equals(loggedInUser.getMobile()))
		.toList();
		
		if(formattedMob.size() < 2)
			throw new AppException(AppErrorCodes.INVALID_PARAMETER_VALUE, "mobileNumbers");
	}
}
