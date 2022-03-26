/**
 * 
 */
package com.tripmaster.tourguide.gpsService.exceptions;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
	 * Handle an UserNotFoundException.
	 * @param e	UserNotFoundException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	ControllerException exceptionHandler(UserNotFoundException e) {
		logException(UserNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), "User not found.");
	}
	
	/**
	 * Handle an NumberFormatException.
	 * @param e	NumberFormatException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	ControllerException exceptionHandler(NumberFormatException e) {
		logException(NumberFormatException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Number format exception.");
	}
	
	/**
	 * Handle an ClassCastException.
	 * @param e	ClassCastException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ClassCastException.class)
	ControllerException exceptionHandler(ClassCastException e) {
		logException(ClassCastException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Class cast exception.");
	}
	
	/**
	 * Handle an IllegalArgumentException.
	 * @param e	IllegalArgumentException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	ControllerException exceptionHandler(IllegalArgumentException e) {
		logException(IllegalArgumentException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Illegal argument exception.");
	}

	/**
	 * Handle ValidationException.
	 * @param e a ValidationException.
	 * @return ControllerException
	 */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ControllerException exceptionHandler(ValidationException e) {
		logException(ValidationException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Validation error.");
    }

    /**
     * Handle ConstraintViolationException.
     * @param e a ConstraintViolationException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ControllerException exceptionHandler(ConstraintViolationException e) {
    	StringBuilder errors = new StringBuilder();
        e.getConstraintViolations().forEach((violation) -> {
        	errors.append(violation.getPropertyPath() + ": " + violation.getMessage() + ", ");
        });
		logException(ConstraintViolationException.class.getSimpleName(), 
				HttpStatus.BAD_REQUEST.value(), errors.toString());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Constraint error.");
    }

    /**
     * Handle MethodArgumentNotValidException.
     * @param e a MethodArgumentNotValidException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ControllerException exceptionHandler(MethodArgumentNotValidException e) {
    	StringBuilder errors = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName + ": " + errorMessage + ", ");
        });
		logException(MethodArgumentNotValidException.class.getSimpleName(), 
				HttpStatus.BAD_REQUEST.value(), errors.toString());
        return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Argument not valid.");
    }

    /**
     * Handle MethodArgumentNotValidException.
     * @param e a MethodArgumentNotValidException.
     * @return ControllerException
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ControllerException exceptionHandler(MethodArgumentTypeMismatchException e) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("There is a problem with the parameter: " + e.getName());
		logException(MethodArgumentTypeMismatchException.class.getSimpleName(), 
				HttpStatus.BAD_REQUEST.value(), sb.toString());
    	return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Argument type not valid.");
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
		logException(Exception.class.getSimpleName(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "An exception occured.");
	}

	private void logException(String exception, int code, String message) {
		LOGGER.error("Catching {}, code: {}, message: {}", exception, code, message);
	}
    
    

}
