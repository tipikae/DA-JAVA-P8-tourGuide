/**
 * 
 */
package com.tripmaster.tourguide.gpsService.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

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
	MVisitedLocation save(MVisitedLocation visitedLocation);
	
	/**
	 * Find all visitedLocations by userId.
	 * @param userId
	 * @return Optional
	 */
	Optional<List<MVisitedLocation>> findByUserId(UUID userId);
	
	/**
	 * Find all visitedLocations.
	 * @return Map
	 */
	Map<UUID, List<MVisitedLocation>> findAll();
}
