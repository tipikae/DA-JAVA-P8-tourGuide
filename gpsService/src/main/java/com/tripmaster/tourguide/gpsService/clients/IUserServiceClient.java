package com.tripmaster.tourguide.gpsService.clients;

import java.util.UUID;

import com.tripmaster.tourguide.gpsService.model.User;

import feign.Param;
import feign.RequestLine;

/**
 * UserService Feign client.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserServiceClient {

	/**
	 * Get a user.
	 * @param userName String
	 * @return User
	 */
	@RequestLine("GET /user/{userName}")
	User getUser(@Param("userName") String userName);
	
	/**
	 * Get a user id.
	 * @param userName String
	 * @return UUID
	 */
	@RequestLine("GET /user")
	UUID getUserId(@Param("userName") String userName);
}
