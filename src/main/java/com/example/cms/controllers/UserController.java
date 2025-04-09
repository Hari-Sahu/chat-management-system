package com.example.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.configs.interceptor.AuthenticationRequired;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.dto.responses.UserDetailsDTO;
import com.example.cms.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "APIs to manage user data")
public class UserController extends TokenAuthenticationController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get user details by ID",
    parameters = {
			@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
			@Parameter(name = "Authorization", description = "Bearer token", required = true)
        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDetailsDTO.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping
    @AuthenticationRequired
    public ResponseEntity<AppResponse> getUser() {
        return ResponseEntity.ok(userService.getUserDetails(getUserAccount()));
    }

    @Operation(summary = "Update user's name",
    		parameters = {
    				@Parameter(name = "X-API-KEY", description = "API key for access", required = true),
    				@Parameter(name = "Authorization", description = "Bearer token", required = true)
    	        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated user",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UserDetailsDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping
    @AuthenticationRequired
    public ResponseEntity<AppResponse> updateUserName(@RequestBody UserRegistrationRequest dto) {
    	dto.validateForUpdate();
    	UserDetailsDTO resBody = userService.updateUser(getUserAccount(), dto.getName());
        return ResponseEntity.ok(resBody);
    }
}
