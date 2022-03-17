/**
 * 
 */
package tourGuide.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.FeignException;

/**
 * Controller exceptions handler.
 * @author tipikae
 * @version 1.0
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	/**
	 * Handle a FeignException.
	 * @param e	FeignException
	 * @return ControllerException
	 */
	@ExceptionHandler(FeignException.class)
	String exceptionHandler(FeignException e, HttpServletResponse response) {
		LOGGER.debug("Catching FeignException: " + e.status() + ": " + e.getMessage());
		response.setStatus(e.status());
		return "Service error";
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
		LOGGER.debug("Catching " + e.getClass().getSimpleName() + ": " + e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "An error occured.");
	}
}
