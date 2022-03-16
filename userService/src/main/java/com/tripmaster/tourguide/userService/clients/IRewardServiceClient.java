/**
 * 
 */
package com.tripmaster.tourguide.userService.clients;

import java.util.UUID;

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
	 */
	@RequestLine("GET /points/{userId}")
	int getUserRewardsPoints(@Param("userId") UUID userId);
}
