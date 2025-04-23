package com.example.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.configs.interceptor.AuthenticationRequired;
import com.example.cms.dto.requests.ConversationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.dto.responses.ConversationResponseObject;
import com.example.cms.services.ConversationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/chats/{chatId}/conversations")
@Tag(name = "Conversations", description = "APIs for sending and retrieving chat conversations")
public class ConversationController extends TokenAuthenticationController {

    @Autowired
    private ConversationService converService;
    
    @Operation(summary = "Send a new conversation message",
            description = "Send a new message within a chat",
    parameters = {
			@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
			@Parameter(name = "Authorization", description = "Bearer token", required = true)
        })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Message sent successfully"),
            @ApiResponse(responseCode = "404", description = "Chat not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    @AuthenticationRequired
    public ResponseEntity<ConversationResponseObject> sendConversation(@RequestBody @Valid ConversationRequest reqDTO, @PathVariable String chatId) {
    	ConversationResponseObject res = converService.sendMessage(getUserAccount(), chatId, reqDTO.getMessage());
        return ResponseEntity.ok(res);
    }
    
	@Operation(summary = "Get list of conversations",
			description = "Returns a list of conversations for a given chat. Supports optional timestamp filter and ETag for caching.",
			parameters = {
				@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
				@Parameter(name = "Authorization", description = "Bearer token", required = true)
			})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conversations retrieved successfully"),
        @ApiResponse(responseCode = "304", description = "Not Modified (ETag matched)")
    })
    @GetMapping
    @AuthenticationRequired
    public ResponseEntity<AppResponse> getConversations(@PathVariable String chatId, @RequestParam(required = false) Long etag) {
		return ResponseEntity.ok(converService.getChatMessages(chatId));
    }
	
	@Operation(summary = "Update a conversation message",
			description = "Updates the content of a specific conversation message identified by its ID.",
			parameters = {
					@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
					@Parameter(name = "Authorization", description = "Bearer token", required = true)
			})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message updated successfully"),
			@ApiResponse(responseCode = "404", description = "Message not found"),
			@ApiResponse(responseCode = "400", description = "Invalid input data")
	})
	@PutMapping("{id}")
	@AuthenticationRequired
	public ResponseEntity<String> updateConversationMessage(@PathVariable String chatId, @PathVariable String id,
			@RequestBody @Valid ConversationRequest reqDTO) {
		converService.updateMessage(chatId, id, reqDTO.getMessage());
		return ResponseEntity.ok("Message updated successfully");
	}
}
