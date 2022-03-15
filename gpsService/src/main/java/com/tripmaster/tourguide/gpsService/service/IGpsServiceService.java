/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.NewVisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;

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
	 */
	VisitedLocation getUserLocation(UUID userId);
	
	/**
	 * Get all users' last visited location.
	 * @return Map<UUID, Location>
	 */
	Map<UUID, Location> getAllUsersLastLocation();
	
	/**
	 * Get a list of user's visited locations.
	 * @param userId UUID
	 * @return List<VisitedLocation>
	 * @throws UserNotFoundException
	 */
	List<VisitedLocation> getUserVisitedLocations(UUID userId) throws UserNotFoundException;
	
	/**
	 * Get the user's last visited location.
	 * @param userId UUID
	 * @return VisitedLocation
	 * @throws UserNotFoundException 
	 */
	VisitedLocation getUserLastVisitedLocation(UUID userId) throws UserNotFoundException;
	
	/**
	 * Add a user's visitedLocation;
	 * @param newVisitedLocationDTO NewVisitedLocationDTO
	 * @return
	 * @throws CustomNumberFormatException
	 */
	VisitedLocation addUserVisitedLocation(NewVisitedLocationDTO newVisitedLocationDTO);
}
