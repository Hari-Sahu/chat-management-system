package com.example.cms.exceptions;

@SuppressWarnings("serial")
public class AppException extends ServiceException {
	
	public AppException(AppErrorCodes appErrorCode) {
		super(appErrorCode.getCode(), appErrorCode.getErrorCode(), appErrorCode.getMessage());
	}
	
	public AppException(AppErrorCodes appErrorCode, Object... params) {
		super(appErrorCode.getCode(), appErrorCode.getErrorCode(), appErrorCode.getMessage(), params);
	}

}
