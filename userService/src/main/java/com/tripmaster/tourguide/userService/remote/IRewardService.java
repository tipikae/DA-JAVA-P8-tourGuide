/**
 * 
 */
package com.tripmaster.tourguide.userService.remote;

import java.util.UUID;

import com.tripmaster.tourguide.userService.exceptions.HttpException;

/**
 * Reward Service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardService {

	/**
	 * Get an user's rewards points.
	 * @param userId UUID
	 * @return int
	 * @throws HttpException 
	 */
	int getUserRewardsPoints(UUID userId) throws HttpException;
}
