/**
 * 
 */
package com.tripmaster.tourguide.gpsService.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.CustomClassCastException;
import com.tripmaster.tourguide.gpsService.exceptions.CustomIllegalArgumentException;
import com.tripmaster.tourguide.gpsService.exceptions.FindByUserIdOperationException;
import com.tripmaster.tourguide.gpsService.exceptions.SaveOperationException;

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
	 * @throws CustomClassCastException 
	 * @throws CustomIllegalArgumentException 
	 * @throws SaveOperationException 
	 */
	VisitedLocation save(VisitedLocation visitedLocation) 
			throws CustomClassCastException, CustomIllegalArgumentException, SaveOperationException;
	
	/**
	 * Find all visitedLocations by userId.
	 * @param userId
	 * @return Optional<List<VisitedLocation>>
	 * @throws CustomClassCastException 
	 * @throws FindByUserIdOperationException 
	 */
	Optional<List<VisitedLocation>> findByUserId(UUID userId) 
			throws CustomClassCastException, FindByUserIdOperationException;
	
	/**
	 * Find all visitedLocations.
	 * @return Map<UUID, List<VisitedLocation>>
	 */
	Map<UUID, List<VisitedLocation>> findAll();
}
