/**
 * 
 */
package com.tripmaster.tourguide.rewardsCentral.service;

import java.util.UUID;

/**
 * RewardCentral service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardCentralService {

	/**
	 * Get attraction reward points.
	 * @param attractionId
	 * @param userId
	 * @return int
	 */
	int getAttractionRewardPoints(UUID attractionId, UUID userId);
}
