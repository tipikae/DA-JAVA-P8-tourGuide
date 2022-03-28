package com.tripmaster.tourguide.rewardService.logging;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Request interceptor for logging.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class CustomRequestLoggingInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRequestLoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
    	StringBuffer requestURL = request.getRequestURL();
    	String query = request.getQueryString();
    	String parameters = request.getParameterMap().keySet().stream()
    			.map(k -> k + ": " + request.getParameterMap().get(k))
    			.collect(Collectors.joining(",", "{", "}"));
        LOGGER.debug("preHandle => Method: {}, Request URL: {}, Query: {}, Parameters: {}", 
        		method, requestURL, query, parameters);
        return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		int status = response.getStatus();
        LOGGER.debug("afterCompletion => Response Status: {}", status);
	}
}
