/**
 * 
 */
package com.tripmaster.tourguide.gpsService.clients;

import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionsAndVisitedLocationsDTO;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * RewardService Feign client.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardServiceClient {

	/**
	 *  Get an attraction reward points.
	 * @param attractionId UUID
	 * @param userId UUID
	 * @return int
	 */
	@RequestLine("GET /reward?attractionId={attractionId}&userId={userId}")
	int getAttractionRewardPoints(@Param("attractionId") UUID attractionId, @Param("userId") UUID userId);
	
	/**
	 * Calculate an user's rewards.
	 * @param userId UUID
	 * @param attractionsAndVisitedLocationsDTO AttractionsAndVisitedLocationsDTO
	 */
	@RequestLine("POST /calculate/{userId}")
    @Headers("Content-Type: application/json")
	void calculateRewards(@Param("userId") UUID userId, 
			AttractionsAndVisitedLocationsDTO attractionsAndVisitedLocationsDTO);
}
