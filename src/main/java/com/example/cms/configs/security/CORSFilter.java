package com.example.cms.configs.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CORSFilter implements Filter {
 
	@Value("${allowedorigins}")
	private String allowedOrigins;
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		  HttpServletResponse response = (HttpServletResponse) res;
		  response.setHeader("Access-Control-Allow-Origin", allowedOrigins);
		  response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		  response.setHeader("Access-Control-Max-Age", "3600");
		  response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, X-CSRF-TOKEN, Content-Type, Accept, enctype, Authorization");
		  chain.doFilter(req, res);
	}
}