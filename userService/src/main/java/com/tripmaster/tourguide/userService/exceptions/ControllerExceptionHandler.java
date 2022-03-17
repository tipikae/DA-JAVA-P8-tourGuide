package com.tripmaster.tourguide.userService.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
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
	 * Handle an UserAlreadyExistsException.
	 * @param e	UserAlreadyExistsException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(UserAlreadyExistsException.class)
	ControllerException exceptionHandler(UserAlreadyExistsException e) {
		LOGGER.debug("Catching UserAlreadyExistsException: " + e.getMessage());
		return new ControllerException(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
	}
	
	/**
	 * Handle an MethodArgumentNotValidException.
	 * @param e	MethodArgumentNotValidException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ControllerException exceptionHandler(MethodArgumentNotValidException e) {
		LOGGER.debug("Catching MethodArgumentNotValidException: " + e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	/**
	 * Handle an MissingPathVariableException.
	 * @param e	MissingPathVariableException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	ControllerException exceptionHandler(MissingPathVariableException e) {
		LOGGER.debug("Catching MissingPathVariableException: " + e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
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
		return new ControllerException(HttpStatus.NOT_FOUND.value(), e.getClass().getSimpleName() + ": " + e.getMessage());
	}

}
