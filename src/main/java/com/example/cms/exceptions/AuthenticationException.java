package com.example.cms.exceptions;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String description;
    private final int errorCode;

    public AuthenticationException(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
}