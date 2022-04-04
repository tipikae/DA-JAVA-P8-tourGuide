/**
 * 
 */
package com.tripmaster.tourguide.gpsService.remoteServices;

import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionsAndVisitedLocationsDTO;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;

/**
 * RewardService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardService {

	/**
	 * Get an attraction's reward points.
	 * @param attractionId UUID
	 * @param userId UUID
	 * @return int
	 * @throws HttpException 
	 */
	int getAttractionRewardPoints(UUID attractionId, UUID userId) throws HttpException;
	
	/**
	 * Calculate rewards.
	 * @param userId UUID
	 * @param attractionsAndVisitedLocationsDTO AttractionsAndVisitedLocationsDTO
	 * @throws HttpException 
	 */
	void calculateRewards(UUID userId, 
			AttractionsAndVisitedLocationsDTO attractionsAndVisitedLocationsDTO) throws HttpException;
}
