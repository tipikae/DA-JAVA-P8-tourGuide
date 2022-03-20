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

import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

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
	 * @return ResponseEntity<List<Attraction>>
	 */
	@GetMapping("/attractions")
	public ResponseEntity<Object> getAttractions() {
		LOGGER.info("getAttractions");
		List<Attraction> attractions = gpsService.getAttractions();
		return new ResponseEntity<Object>(attractions, HttpStatus.OK);
	}
	
	/**
	 * Get a user's location.
	 * @param userName String
	 * @return ResponseEntity<VisitedLocation>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/location/{userName}")
	public ResponseEntity<Object> getUserLocation(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException {
		LOGGER.info("getUserLocation: userName=" + userName);
		VisitedLocation visitedLocation = gpsService.getUserLocation(userName);
		return new ResponseEntity<Object>(visitedLocation, HttpStatus.OK);
	}
	
	/**
	 * Get all users' last location.
	 * @return ResponseEntity<Map<UUID, Location>>
	 */
	@GetMapping("/lastlocations")
	public ResponseEntity<Object> getAllUsersLastLocation() {
		LOGGER.info("getAllUsersLastLocation");
		Map<UUID, Location> allUsersLastLocation = gpsService.getAllUsersLastLocation();
		return new ResponseEntity<Object>(allUsersLastLocation, HttpStatus.OK);
	}
	
	/**
	 * Get a user's all visited locations.
	 * @param userId - UUID
	 * @return ResponseEntity<List<VisitedLocation>>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/locations/{userId}")
	public ResponseEntity<Object> getUserVisitedLocations(@PathVariable("userId") @NotNull UUID userId) 
			throws UserNotFoundException {
		LOGGER.info("getUserVisitedLocations: userId=" + userId);
		List<VisitedLocation> visitedLocations = gpsService.getUserVisitedLocations(userId);
		return new ResponseEntity<Object>(visitedLocations, HttpStatus.OK);
	}
	
	/**
	 * Get nearby attractions.
	 * @param userId UUID
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/nearbyattractions/{userName}")
	public ResponseEntity<Object> getNearByAttractions(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException {
		LOGGER.info("getNearByAttractions: userName=" + userName);
		List<Attraction> attractions = gpsService.getNearByAttractions(userName);
		return new ResponseEntity<Object>(attractions, HttpStatus.OK);
	}

}
