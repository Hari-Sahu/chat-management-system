package com.example.cms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.cms.utils.JwtUtil;

@Configuration
public class AppConfig {
	
	@Bean
	public JwtUtil jwtToken() {
		return new JwtUtil();
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
