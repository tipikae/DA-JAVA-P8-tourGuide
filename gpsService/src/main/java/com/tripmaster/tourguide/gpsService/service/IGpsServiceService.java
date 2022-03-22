/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceService {

	/**
	 * Get all attractions.
	 * @return List<AttractionDTO>
	 */
	List<AttractionDTO> getAttractions();
	
	/**
	 * Get user current location.
	 * @param username String
	 * @return VisitedLocationDTO
	 */
	VisitedLocationDTO getUserLocation(String username) throws UserNotFoundException;
	
	/**
	 * Get all users' last visited location.
	 * @return Map<UUID, LocationDTO>
	 * @throws ConverterException 
	 */
	Map<UUID, LocationDTO> getAllUsersLastLocation() throws ConverterException;
	
	/**
	 * Get a list of user's visited locations.
	 * @param userId - UUID
	 * @return List<VisitedLocationDTO>
	 * @throws UserNotFoundException
	 * @throws ConverterException 
	 */
	List<VisitedLocationDTO> getUserVisitedLocations(UUID userId) 
			throws UserNotFoundException, ConverterException;
	
	/**
	 * Get an users's nearby attractions.
	 * @param username String
	 * @return List<AttractionDTO>
	 * @throws UserNotFoundException
	 */
	List<AttractionDTO> getNearByAttractions(String username) throws UserNotFoundException;
}
