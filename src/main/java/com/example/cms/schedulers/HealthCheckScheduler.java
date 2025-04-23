package com.example.cms.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthCheckScheduler {
	
	@Autowired
    private RestTemplate restTemplate;
	
	private static final String URL = "http://localhost:9080/api/healthcheck";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckScheduler.class);

	// Cron: every 10 minutes
    @Scheduled(cron = "0 */10 * * * *")
    public void performHealthCheck() {
    	LOGGER.info("Running health check at {}", java.time.LocalDateTime.now());
    	try {
            String response = restTemplate.getForObject(URL, String.class);
            LOGGER.info("Health Check Response: {}", response);
        } catch (Exception e) {
        	LOGGER.error("Health check failed: {}", e.getMessage());
        }
    }
}
