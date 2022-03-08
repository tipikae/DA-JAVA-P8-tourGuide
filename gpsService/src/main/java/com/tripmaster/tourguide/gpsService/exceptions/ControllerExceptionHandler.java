/**
 * 
 */
package com.tripmaster.tourguide.gpsService.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle controller exceptions.
 * @author tipikae
 * @version 1.0
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	/**
	 * Handle a CustomNumberFormatException.
	 * @param e	CustomNumberFormatException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomNumberFormatException.class)
	ControllerException exceptionHandler(CustomNumberFormatException e) {
		LOGGER.debug("Catching CustomNumberFormatException: " + e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
	
	/**
	 * Handle an Exception.
	 * @param e	Exception
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	ControllerException exceptionHandler(Exception e) {
		LOGGER.debug("Catching Exception: " + e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

}
