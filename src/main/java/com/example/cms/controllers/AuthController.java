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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController extends TokenAuthenticationController {

	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AppResponse> register(@Valid @RequestBody UserRegistrationRequest dto) {
		return ResponseEntity.ok(authService.registerUser(dto));
	}

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
	
	@PostMapping("/logout")
	@AuthenticationRequired
    public ResponseEntity<String> logout() {
		authService.logout(getAccount());
		return ResponseEntity.ok("Logged out successfully");
    }
}
