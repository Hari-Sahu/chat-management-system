package com.example.cms.exceptions;

public enum ServiceErrorCodes {
	
	SYSTEM_ERROR("SYSTEM_ERROR", 2001, "System Error"),
	DATA_NOT_FOUND("DATA_NOT_FOUND", 2002, "%s data not found"),
	MOBILE_NUMBER_EXIST("MOBILE_NUMBER_EXIST", 2003, "Mobile number already registered"),
	ALREADY_LOGIN("ALREADY_LOGIN", 2004, "User is already logged in with an active session."),
	;
	private String message;
	private int errorCode;
	private String code;
	
	private ServiceErrorCodes(String code, int errorCode, String message) {
		this.message = message;
		this.errorCode = errorCode;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public String getCode() {
		return code;
	}
	public int getErrorCode() {
		return errorCode;
	}
}
