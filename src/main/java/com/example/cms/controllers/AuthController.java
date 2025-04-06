package com.example.cms.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.requests.LoginRequest;
import com.example.cms.dto.requests.UserRegistrationRequest;
import com.example.cms.dto.responses.AppResponse;
import com.example.cms.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/healthcheck")
	public ResponseEntity<AppResponse> healthCheck() {
		return ResponseEntity.ok(new AppResponse());
	}

	@PostMapping("/register")
	public ResponseEntity<AppResponse> register(@Valid @RequestBody UserRegistrationRequest dto) {
		return ResponseEntity.ok(userService.registerUser(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest dto) {
		String token = userService.login(dto.getMobileNumber(), dto.getPassword());
		if (token != null) {
			Map<String, Object> res = new HashMap<>();
			res.put("statusCode", 200);
			res.put("token", token);
			
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
}
