package com.example.cms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.cms.dao.entities.AccountActivity;
import com.example.cms.dao.entities.User;
import com.example.cms.exceptions.AuthenticationException;
import com.example.cms.services.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

public abstract class TokenAuthenticationController {
	
	@Autowired private AuthenticationService authService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationController.class);
	
	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	protected AccountActivity getAccount() {
		LOGGER.debug("Inside the base controller");
		HttpServletRequest request = getRequest();
		String token = (String) request.getAttribute("token");
		
		if(token == null) {
			throw new AuthenticationException(HttpStatus.BAD_REQUEST.value(), "Invalid Request - token is missing");
		}
		LOGGER.debug("Getting instance of AuthenticationService {}", authService.getClass());
		AccountActivity account = authService.getAccountByToken(token);
		if(account == null) {
			throw new AuthenticationException(HttpStatus.UNAUTHORIZED.value(), "Not Authorized");
		}
		return account;
	}
	
	protected User getUserAccount() {
		AccountActivity account = getAccount();
		return account.getUser();
	}
}
