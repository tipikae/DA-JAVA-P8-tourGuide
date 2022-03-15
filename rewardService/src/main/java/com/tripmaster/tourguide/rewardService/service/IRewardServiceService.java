/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Reward;

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
	 * @param userId UUID
	 * @return List<Reward>
	 * @throws UserNotFoundException
	 */
	List<Reward> getUserRewards(UUID userId) throws UserNotFoundException;
	
	/**
	 * Get a user's rewards points sum.
	 * @param userId UUID
	 * @return int
	 * @throws UserNotFoundException
	 */
	int getUserRewardsPoints(UUID userId) throws UserNotFoundException;
	
	/**
	 * Get nearby attractions.
	 * @param userId UUID
	 * @return List<Attraction>
	 * @throws UserNotFoundException
	 */
	List<Attraction> getNearByAttractions(UUID userId) throws UserNotFoundException;
}
