package com.example.cms.configs.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter {
	
	private final String requiredApiKey;
	
	public ApiKeyFilter(String apiKey) {
		this.requiredApiKey = apiKey;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {		
		String apiKey = request.getHeader("X-API-KEY");
	    if (!requiredApiKey.equals(apiKey)) {
	      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	      response.getWriter().write("Invalid API key");
	      return;
	    }
	    	    
	    // Set authentication in context to avoid "pre-auth" error
        Authentication auth = new UsernamePasswordAuthenticationToken(requiredApiKey, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
	    filterChain.doFilter(request, response);
	}
}
