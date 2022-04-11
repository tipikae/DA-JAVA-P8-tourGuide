/**
 * 
 */
package com.tripmaster.tourguide.userService.exceptions;

/**
 * User already exists exception.
 * @author tipikae
 *  @version 1.0
 *
 */
public class UserAlreadyExistsException extends UserServiceException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String arg0) {
		super(arg0);
	}

}
