package com.example.cms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.responses.AppResponse;

@RestController
public class HealthCheckController {

	@GetMapping("/healthcheck")
	public ResponseEntity<AppResponse> healthCheck() {
		return ResponseEntity.ok(new AppResponse());
	}
}
