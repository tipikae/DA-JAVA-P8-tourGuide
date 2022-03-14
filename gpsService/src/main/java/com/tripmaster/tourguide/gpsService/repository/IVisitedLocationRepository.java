/**
 * 
 */
package com.tripmaster.tourguide.gpsService.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;

/**
 * VisitedLocation repository definition.
 * @author tipikae
 *  @version 1.0
 *
 */
public interface IVisitedLocationRepository {

	/**
	 * Save a visitedLocation.
	 * @param visitedLocation
	 * @return VisitedLocation
	 */
	VisitedLocation save(VisitedLocation visitedLocation);
	
	/**
	 * Find all visitedLocations by userId.
	 * @param userId
	 * @return Optional<List<VisitedLocation>>
	 */
	Optional<List<VisitedLocation>> findByUserId(UUID userId);
	
	/**
	 * Find all visitedLocations.
	 * @return Map<UUID, List<VisitedLocation>>
	 */
	Map<UUID, List<VisitedLocation>> findAll();
}
