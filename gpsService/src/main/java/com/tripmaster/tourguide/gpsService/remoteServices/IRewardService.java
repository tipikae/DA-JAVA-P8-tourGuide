/**
 * 
 */
package com.tripmaster.tourguide.gpsService.remoteServices;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
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
	 * @param visitedLocationDTOs List<VisitedLocationDTO>
	 * @param attractionDTOs List<AttractionDTO>
	 * @throws HttpException 
	 */
	void calculateRewards(UUID userId, List<VisitedLocationDTO> visitedLocationDTOs, 
			List<AttractionDTO> attractionDTOs) throws HttpException;
}
