package com.tripmaster.tourguide.gpsService.clients;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpUserNotFoundException;

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

	/**
	 * Get all userIds.
	 * @return List
	 * @throws HttpException
	 */
	@RequestLine("GET /userIds")
	List<UUID> getAllUserIds() throws HttpException;
}
