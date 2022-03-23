/**
 * 
 */
package com.tripmaster.tourguide.rewardService.remoteServices;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsService {

	/**
	 * Get attractions.
	 * @return List<Attraction>
	 * @throws HttpException 
	 */
	List<Attraction> getAttractions() throws HttpException;
	
	/**
	 * Get an user's visitedLocations.
	 * @param userId UUID
	 * @return List<VisitedLocation>
	 * @throws HttpException 
	 */
	List<VisitedLocation> getUserVisitedLocations(UUID userId) throws HttpException;
}
