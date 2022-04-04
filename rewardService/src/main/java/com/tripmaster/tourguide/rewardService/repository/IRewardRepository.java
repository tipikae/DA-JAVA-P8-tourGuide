/**
 * 
 */
package com.tripmaster.tourguide.rewardService.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tripmaster.tourguide.rewardService.model.Reward;

/**
 * Reward repository interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardRepository {

	/**
	 * Save a user's reward.
	 * @param reward
	 * @return Reward
	 */
	Reward save(Reward reward);
	
	/**
	 * Find user's rewards.
	 * @param userId
	 * @return Optional
	 */
	Optional<List<Reward>> findByUserId(UUID userId);
}
