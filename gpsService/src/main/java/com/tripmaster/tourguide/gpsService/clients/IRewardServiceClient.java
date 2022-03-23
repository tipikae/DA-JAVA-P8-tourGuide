/**
 * 
 */
package com.tripmaster.tourguide.gpsService.clients;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;

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
	@RequestLine("GET /reward")
	int getAttractionRewardPoints(@Param("attractionId") UUID attractionId, @Param("userId") UUID userId);
	
	@RequestLine("GET /calculate")
	void calculateRewards(
			@Param("userId") UUID userId, 
			@Param("visitedLocations") List<VisitedLocationDTO> visitedLocations,
			@Param("attractions") List<AttractionDTO> attractions);
}
