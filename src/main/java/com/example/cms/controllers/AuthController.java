package com.example.cms.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.configs.interceptor.AuthenticationRequired;
import com.example.cms.dto.requests.LoginRequest;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.services.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth APIs", description = "Authentication related endpoints")
public class AuthController extends TokenAuthenticationController {

	@Autowired
	private AuthenticationService authService;
	
	@Operation(summary = "Register a new user",
			parameters = {
		            @Parameter(
		                name = "X-API-KEY",
		                in = ParameterIn.HEADER,
		                required = true,
		                description = "API key for access"
		            )
		        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or mobile already exists")
    })
	@PostMapping("/register")
	public ResponseEntity<AppResponse> register(@Valid @RequestBody UserRegistrationRequest dto) {
		return ResponseEntity.ok(authService.registerUser(dto));
	}

	@Operation(summary = "Login with mobile and password",
			parameters = {
		            @Parameter(
		                name = "X-API-KEY",
		                in = ParameterIn.HEADER,
		                required = true,
		                description = "API key for access"
		            )
		        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful with token"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest dto) {
		String token = authService.login(dto.getMobileNumber(), dto.getPassword());
		if (StringUtils.isNotBlank(token)) {
			Map<String, Object> res = new HashMap<>();
			res.put("statusCode", 200);
			res.put("token", token);
			
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
	
	@Operation(summary = "Logout by deleting token",
			parameters = {
		            @Parameter(
		                name = "X-API-KEY",
		                in = ParameterIn.HEADER,
		                required = true,
		                description = "API key for access"
		            )
		        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logged out successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized or token missing")
    })
	@PostMapping("/logout")
	@AuthenticationRequired
    public ResponseEntity<String> logout() {
		authService.logout(getAccount());
		return ResponseEntity.ok("Logged out successfully");
    }
}
