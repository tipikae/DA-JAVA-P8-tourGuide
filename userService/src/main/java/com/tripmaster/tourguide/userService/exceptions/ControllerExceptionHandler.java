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
		logException(e.getClass().getSimpleName(), HttpStatus.METHOD_NOT_ALLOWED.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.METHOD_NOT_ALLOWED.value(), "User already exists.");
	}
	
	/**
	 * Handle an UserNotFoundException.
	 * @param e	UserNotFoundException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	ControllerException exceptionHandler(UserNotFoundException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), "User not found.");
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
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Argument not valid.");
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
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Missing path variable.");
	}
	
	/**
	 * Handle an HttpUserNotFoundException.
	 * @param e	HttpUserNotFoundException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(HttpUserNotFoundException.class)
	ControllerException exceptionHandler(HttpUserNotFoundException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), "User not found.");
	}
	
	/**
	 * Handle an HttpBadRequestException.
	 * @param e	HttpBadRequestException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpBadRequestException.class)
	ControllerException exceptionHandler(HttpBadRequestException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Bad request.");
	}
	
	/**
	 * Handle an HttpException.
	 * @param e	HttpException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpException.class)
	ControllerException exceptionHandler(HttpException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Service unavailable.");
	}
	
	/**
	 * Handle an Exception.
	 * @param e	Exception
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	ControllerException exceptionHandler(Exception e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "An error occured.");
	}

	private void logException(String exception, int code, String message) {
		LOGGER.error("Catching {}, code: {}, message: {}", exception, code, message);
	}

}
