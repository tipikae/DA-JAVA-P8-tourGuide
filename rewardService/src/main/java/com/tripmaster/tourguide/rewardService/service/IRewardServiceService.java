/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;

/**
 * RewardService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardServiceService {

	/**
	 * Calculate rewards.
	 * @param userId UUID
	 * @throws UserNotFoundException
	 */
	void calculateRewards(UUID userId) throws UserNotFoundException;
	
	/**
	 * Get a user's rewards.
	 * @param userName String
	 * @return List<RewardDTO>
	 * @throws UserNotFoundException
	 */
	List<RewardDTO> getUserRewards(String userName) throws UserNotFoundException;
	
	/**
	 * Get a user's rewards points sum.
	 * @param userId UUID
	 * @return int
	 * @throws UserNotFoundException
	 */
	int getUserRewardsPoints(UUID userId) throws UserNotFoundException;
}
