/**
 * 
 */
package com.tripmaster.tourguide.userService.clients;

import java.util.UUID;

import com.tripmaster.tourguide.userService.exceptions.HttpUserNotFoundException;

import feign.Param;
import feign.RequestLine;

/**
 * Feign client for RewardService.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardServiceClient {

	/**
	 * Get attraction reward points.
	 * @param userId
	 * @return int
	 * @throws HttpUserNotFoundException
	 */
	@RequestLine("GET /points/{userId}")
	int getUserRewardsPoints(@Param("userId") UUID userId) throws HttpUserNotFoundException;
}
