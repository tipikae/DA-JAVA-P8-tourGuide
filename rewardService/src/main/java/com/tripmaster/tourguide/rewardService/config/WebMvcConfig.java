package com.tripmaster.tourguide.rewardService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration.
 * @author tipikae
 * @version 1.0
 *
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private HandlerInterceptor requestLoggingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLoggingInterceptor);
	}
}
