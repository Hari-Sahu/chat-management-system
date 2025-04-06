package com.example.cms.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cms.dto.responses.AppResponse;
import com.example.cms.dto.responses.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice  
@ResponseBody 
public class CMSExceptionHandler {
	
	private static final String DEFAULT_FAILURE_MESSAGE = "Sorry, could not process your request at the moment. Please try again after sometime.";

	private static final Logger LOGGER = LoggerFactory.getLogger(CMSExceptionHandler.class);
	
	@ExceptionHandler(value= {ServiceException.class, AppException.class})
	public AppResponse handleServiceException(HttpServletRequest request, HttpServletResponse response, ServiceException ae) {
		AppResponse resp = new AppResponse();
		resp.setError(new ErrorResponse(ae.getErrorCode(), ae.getMessage()));
		return resp;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

	@ExceptionHandler(value=Throwable.class)
	public AppResponse handleException(HttpServletRequest request, HttpServletResponse response, Throwable tr) {
		LOGGER.error("Unexpected error - ", tr);
		AppResponse resp = new AppResponse();
		resp.setError(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_FAILURE_MESSAGE));
		resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return resp;
	}
}