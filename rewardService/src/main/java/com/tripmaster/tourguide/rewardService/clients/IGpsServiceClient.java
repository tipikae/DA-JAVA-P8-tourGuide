/**
 * 
 */
package com.tripmaster.tourguide.rewardService.clients;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

import feign.Param;
import feign.RequestLine;

/**
 * GpsService Feign client.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceClient {

	/**
	 * Get attractions.
	 * @return List<Attraction>
	 */
	@RequestLine("GET /attractions")
	List<Attraction> getAttractions();
	
	/**
	 * Get an user's visitedLocations.
	 * @param userId UUID
	 * @return List<VisitedLocation>
	 */
	@RequestLine("GET /locations")
	List<VisitedLocation> getUserVisitedLocations(@Param UUID userId);
}
