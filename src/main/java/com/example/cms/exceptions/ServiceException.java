package com.example.cms.exceptions;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String code;
	private final int errorCode;

	public ServiceException(ServiceErrorCodes errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.errorCode = errorCode.getErrorCode();
	}

	public ServiceException(ServiceErrorCodes errorCode, Object... params) {
		super(String.format(errorCode.getMessage(), params));
		this.code = errorCode.getCode();
		this.errorCode = errorCode.getErrorCode();
	}
	
	protected ServiceException(String code, int errorCode, String message, Object... params) {
		super(String.format(message, params));
		this.code = code;
		this.errorCode = errorCode;
	}

	public String getCode() {
		return code;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
