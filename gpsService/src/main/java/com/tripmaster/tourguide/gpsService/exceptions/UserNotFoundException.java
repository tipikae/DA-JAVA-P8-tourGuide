/**
 * 
 */
package com.tripmaster.tourguide.gpsService.exceptions;

/**
 * User not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class UserNotFoundException extends GpsServiceException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String arg0) {
		super(arg0);
	}

}
