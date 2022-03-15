/**
 * 
 */
package com.tripmaster.tourguide.gpsService.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.gpsService.dto.NewVisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * GpsUtil rest controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/gpsservice")
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
	 * @param userId - UUID
	 * @return ResponseEntity<VisitedLocation>
	 */
	@GetMapping("/location")
	public ResponseEntity<Object> getUserLocation(@RequestParam @NotNull UUID userId) {
		LOGGER.info("getUserLocation: userId=" + userId);
		VisitedLocation visitedLocation = gpsService.getUserLocation(userId);
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
	@GetMapping("/locations")
	public ResponseEntity<Object> getUserVisitedLocations(@RequestParam @NotNull UUID userId) 
			throws UserNotFoundException {
		LOGGER.info("getUserVisitedLocations: userId=" + userId);
		List<VisitedLocation> visitedLocations = gpsService.getUserVisitedLocations(userId);
		return new ResponseEntity<Object>(visitedLocations, HttpStatus.OK);
	}
	
	/**
	 * Get a user's last location.
	 * @param userId - UUID
	 * @return ResponseEntity<VisitedLocation>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/lastlocation")
	public ResponseEntity<Object> getUserLastVisitedLocation(@RequestParam @NotNull UUID userId)
			throws UserNotFoundException {
		LOGGER.info("getUserLastVisitedLocation: userId=" + userId);
		VisitedLocation visitedLocation = gpsService.getUserLastVisitedLocation(userId);
		return new ResponseEntity<Object>(visitedLocation, HttpStatus.OK);
	}
	
	/**
	 * Add a users's visitedLocation.
	 * @param newVisitedLocationDTO - NewVisitedLocationDTO
	 * @return ResponseEntity<VisitedLocation>
	 */
	@PostMapping(value = "/location", consumes = {"application/json"})
	public ResponseEntity<Object> addUserVisitedLocation(
			@RequestBody @Valid NewVisitedLocationDTO newVisitedLocationDTO) {
		LOGGER.info("addUserVisitedLocation");
		VisitedLocation response = gpsService.addUserVisitedLocation(newVisitedLocationDTO);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
