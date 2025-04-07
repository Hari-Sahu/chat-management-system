package com.example.cms.configs.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.cms.exceptions.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod handlerMethod
        		&& handlerMethod.getMethodAnnotation(AuthenticationRequired.class) == null) {
        	return true;
        }
        return handleAuthorization(request);
    }

    private boolean handleAuthorization(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = getAuthorizationToken(authHeader);
        if(token == null) {
            throw new AuthenticationException(HttpStatus.BAD_REQUEST.value(), "Invalid Request - token is missing");
        }
        request.setAttribute("token", token);
        return handleBearerAuthorization(token);
    }

    private boolean handleBearerAuthorization(String token) {
		/*
		 * NOTE: checking only existence of token and not validating here, 
		 * validation is being performed at required API controllers
		 */
    	return !StringUtils.isEmpty(token);
    }

    private String getAuthorizationToken(String authHeader) {
        if(StringUtils.isBlank(authHeader) && !authHeader.startsWith("Bearer ")) {
        	LOGGER.debug("auth header is null");
            return null;
        }
        String[] basicTokens = authHeader.split("\\s");
        if(basicTokens.length != 2) {
            return null;
        }
        return basicTokens[1];
    }
}
