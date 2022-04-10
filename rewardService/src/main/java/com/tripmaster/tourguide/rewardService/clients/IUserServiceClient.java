/**
 * 
 */
package com.tripmaster.tourguide.rewardService.clients;

import java.util.UUID;

import com.tripmaster.tourguide.rewardService.exceptions.HttpUserNotFoundException;

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
	 * Get a user id.
	 * @param userName String
	 * @return UUID
	 * @throws HttpUserNotFoundException
	 */
	@RequestLine("GET /user?userName={userName}")
	UUID getUserId(@Param("userName") String userName) throws HttpUserNotFoundException;
}
