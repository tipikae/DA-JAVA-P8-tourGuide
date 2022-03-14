/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceService {

	/**
	 * Get all attractions.
	 * @return List<Attraction>
	 */
	List<Attraction> getAttractions();
	
	/**
	 * Get user current location.
	 * @param userId UUID
	 * @return VisitedLocation
	 * @throws CustomNumberFormatException
	 */
	VisitedLocation getUserLocation(UUID userId) throws CustomNumberFormatException;
	
	/**
	 * Get all users' last visited location.
	 * @return Map<UUID, Location>
	 * @throws CustomNumberFormatException
	 */
	Map<UUID, Location> getAllUsersLastLocation() throws CustomNumberFormatException;
	
	/**
	 * Get a list of user's visited locations.
	 * @param userId UUID
	 * @return List<VisitedLocation>
	 * @throws CustomNumberFormatException
	 */
	List<VisitedLocation> getUserVisitedLocations(UUID userId) throws CustomNumberFormatException;
	
	/**
	 * Get the user's last visited location.
	 * @param userId UUID
	 * @return VisitedLocation
	 * @throws CustomNumberFormatException
	 */
	VisitedLocation getUserLastVisitedLocation(UUID userId) throws CustomNumberFormatException;
	
	/**
	 * Add a user's visitedLocation;
	 * @param visitedLocation VisitedLocation
	 * @return
	 * @throws CustomNumberFormatException
	 */
	VisitedLocation addUserVisitedLocation(VisitedLocation visitedLocation) 
			throws CustomNumberFormatException;
}
