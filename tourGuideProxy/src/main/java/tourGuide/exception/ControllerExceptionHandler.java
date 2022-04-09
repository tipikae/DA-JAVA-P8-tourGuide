/**
 * 
 */
package tourGuide.exception;

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
	 * Handle an NotFoundException.
	 * @param e	NotFoundException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	ControllerException exceptionHandler(NotFoundException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), "User not found.");
	}
	
	/**
	 * Handle an AlreadyExistException.
	 * @param e	AlreadyExistException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(AlreadyExistException.class)
	ControllerException exceptionHandler(AlreadyExistException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.METHOD_NOT_ALLOWED.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.METHOD_NOT_ALLOWED.value(), "User already exists.");
	}
	
	/**
	 * Handle an BadRequestException.
	 * @param e	BadRequestException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	ControllerException exceptionHandler(BadRequestException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Bad request.");
	}
	
	/**
	 * Handle an HttpClientException.
	 * @param e	HttpClientException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpClientException.class)
	ControllerException exceptionHandler(HttpClientException e) {
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
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "An exception occured.");
	}

	private void logException(String exception, int code, String message) {
		LOGGER.error("Catching {}, code: {}, message: {}", exception, code, message);
	}
    
    

}
