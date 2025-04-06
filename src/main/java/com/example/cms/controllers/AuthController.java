package com.example.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
