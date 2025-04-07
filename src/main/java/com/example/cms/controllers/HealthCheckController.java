package com.example.cms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.responses.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Healthcheck API")
public class HealthCheckController {

	@Operation(
	        parameters = {
	            @Parameter(
	                name = "X-API-KEY",
	                in = ParameterIn.HEADER,
	                required = true,
	                description = "API key for access"
	            )
	        }
	    )
	@GetMapping("/healthcheck")
	public ResponseEntity<AppResponse> healthCheck() {
		return ResponseEntity.ok(new AppResponse());
	}
}
