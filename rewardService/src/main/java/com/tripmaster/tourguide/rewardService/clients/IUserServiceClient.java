/**
 * 
 */
package com.tripmaster.tourguide.rewardService.clients;

import com.tripmaster.tourguide.rewardService.model.User;

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
}
