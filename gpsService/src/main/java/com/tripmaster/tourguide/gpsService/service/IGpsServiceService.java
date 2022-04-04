/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.TrackLocationException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceService {

	/**
	 * Get all attractions.
	 * @return List
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 */
	List<AttractionDTO> getAttractions() throws ConverterDTOException, ConverterLibException;
	
	/**
	 * Get user current location.
	 * @param username String
	 * @return VisitedLocationDTO
	 * @throws HttpException 
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 * @throws TrackLocationException 
	 */
	VisitedLocationDTO getUserLocation(String username) 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException, 
				TrackLocationException;
	
	/**
	 * Get all users' last visited location.
	 * @return Map
	 * @throws ConverterDTOException 
	 */
	Map<UUID, LocationDTO> getAllUsersLastLocation() throws ConverterDTOException;
	
	/**
	 * Get a list of user's visited locations.
	 * @param userId - UUID
	 * @return List
	 * @throws UserNotFoundException
	 * @throws ConverterDTOException 
	 */
	List<VisitedLocationDTO> getUserVisitedLocations(UUID userId) 
			throws UserNotFoundException, ConverterDTOException;
	
	/**
	 * Get an users's nearby attractions.
	 * @param username String
	 * @return List
	 * @throws UserNotFoundException
	 * @throws HttpException 
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 */
	List<NearByAttractionDTO> getNearByAttractions(String username) 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException;

	/**
	 * Track user location
	 * @param userId UUID
	 * @return MVisitedLocation
	 * @throws ConverterLibException
	 * @throws HttpException
	 * @throws TrackLocationException 
	 */
	MVisitedLocation trackUserLocation(UUID userId) 
			throws ConverterLibException, HttpException, TrackLocationException;
}
