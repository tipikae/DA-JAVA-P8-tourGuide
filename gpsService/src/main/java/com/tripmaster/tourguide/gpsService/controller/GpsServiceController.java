/**
 * 
 */
package com.tripmaster.tourguide.gpsService.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

/**
 * GpsService rest controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/gpsservice")
@Validated
public class GpsServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GpsServiceController.class);
	
	@Autowired
	private IGpsServiceService gpsService;
	
	/**
	 * Get all attractions.
	 * @return ResponseEntity<List<AttractionDTO>>
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 */
	@GetMapping("/attractions")
	public ResponseEntity<Object> getAttractions() throws ConverterDTOException, ConverterLibException {
		LOGGER.info("getAttractions");
		List<AttractionDTO> attractions = gpsService.getAttractions();
		return new ResponseEntity<Object>(attractions, HttpStatus.OK);
	}
	
	/**
	 * Get a user's location.
	 * @param userName String
	 * @return ResponseEntity<LocationDTO>
	 * @throws UserNotFoundException 
	 * @throws HttpException 
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 */
	@GetMapping("/location/{userName}")
	public ResponseEntity<Object> getUserLocation(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		LOGGER.info("getUserLocation: userName=" + userName);
		LocationDTO visitedLocation = gpsService.getUserLocation(userName);
		return new ResponseEntity<Object>(visitedLocation, HttpStatus.OK);
	}
	
	/**
	 * Get all users' last location.
	 * @return ResponseEntity<Map<UUID, LocationDTO>>
	 * @throws ConverterDTOException 
	 */
	@GetMapping("/lastlocations")
	public ResponseEntity<Object> getAllUsersLastLocation() throws ConverterDTOException {
		LOGGER.info("getAllUsersLastLocation");
		Map<UUID, LocationDTO> allUsersLastLocation = gpsService.getAllUsersLastLocation();
		return new ResponseEntity<Object>(allUsersLastLocation, HttpStatus.OK);
	}
	
	/**
	 * Get a user's all visited locations.
	 * @param userId - UUID
	 * @return ResponseEntity<List<VisitedLocationDTO>>
	 * @throws UserNotFoundException 
	 * @throws ConverterDTOException 
	 */
	@GetMapping("/locations/{userId}")
	public ResponseEntity<Object> getUserVisitedLocations(@PathVariable("userId") @NotNull UUID userId) 
			throws UserNotFoundException, ConverterDTOException {
		LOGGER.info("getUserVisitedLocations: userId=" + userId);
		List<VisitedLocationDTO> visitedLocations = gpsService.getUserVisitedLocations(userId);
		return new ResponseEntity<Object>(visitedLocations, HttpStatus.OK);
	}
	
	/**
	 * Get nearby attractions.
	 * @param userName String
	 * @return ResponseEntity<List<NearByAttractionDTO>>
	 * @throws UserNotFoundException 
	 * @throws HttpException 
	 * @throws ConverterLibException 
	 * @throws ConverterDTOException 
	 */
	@GetMapping("/nearbyattractions/{userName}")
	public ResponseEntity<Object> getNearByAttractions(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		LOGGER.info("getNearByAttractions: userName=" + userName);
		List<NearByAttractionDTO> attractions = gpsService.getNearByAttractions(userName);
		return new ResponseEntity<Object>(attractions, HttpStatus.OK);
	}

}
