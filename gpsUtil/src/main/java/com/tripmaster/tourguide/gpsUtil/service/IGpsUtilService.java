/**
 * 
 */
package com.tripmaster.tourguide.gpsUtil.service;

import java.util.List;
import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

/**
 * Interface for accessing gpsUtil lib.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsUtilService {

	/**
	 * Get all attractions.
	 * @return List<Attraction>
	 */
	List<Attraction> getAttractions();
	
	/**
	 * Get user current location.
	 * @param userId UUID
	 * @return VisitedLocation
	 */
	VisitedLocation getUserLocation(UUID userId);
}
