package com.example.cms.exceptions;

public enum AppErrorCodes {

	//General Errors
	INVALID_PARAMETER_FORMAT("INVALID_PARAMETER_FORMAT", 1001, "Parameter %s is not in valid format."),
	PARAMETER_MISSING("PARAMETER_MISSING", 1002, "Missing the required parameter %s"),
	INVALID_PARAMETER_VALUE("INVALID_PARAMETER_VALUE", 1003, "Parameter %s doesn't have the valid value"),
	;	
	
	private String message;
	private String code;
	private int errorCode;
	
	private AppErrorCodes(String code, int errorCode, String message) {
		this.message = message;
		this.code = code;
		this.errorCode = errorCode;
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