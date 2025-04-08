package com.example.cms.controllers;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.configs.interceptor.AuthenticationRequired;
import com.example.cms.services.ChatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/chats")
@Tag(name = "Chat", description = "APIs for managing chats")
public class ChatController extends TokenAuthenticationController {

    @Autowired
    private ChatService chatService;

    @Operation(summary = "Initiate Chat",
            description = "Initiates a one-to-one chat with another user using their mobile number.",
    parameters = {
			@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
			@Parameter(name = "Authorization", description = "Bearer token", required = true)
        })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Chat initiated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/initiate")
    @AuthenticationRequired
    public ResponseEntity<String> initiateChat(@RequestBody JsonObject reqBody) {
    	validateInitiateChatRequest(reqBody);
    	chatService.initiateChat(getUserAccount(), null);
        return ResponseEntity.ok("Chat initiated successfully");
    }
    
    private void validateInitiateChatRequest(JsonObject reqBody) {
//    	if(reqBody.)
    }
}
