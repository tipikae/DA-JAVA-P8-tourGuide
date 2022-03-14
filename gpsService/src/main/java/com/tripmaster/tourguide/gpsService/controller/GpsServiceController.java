/**
 * 
 */
package com.tripmaster.tourguide.gpsService.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

import gpsUtil.location.Attraction;
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
	private IGpsServiceService gpsUtilService;
	
	@GetMapping("/attractions")
	public ResponseEntity<Object> getAttractions() throws Exception {
		LOGGER.info("getAttractions");
		List<Attraction> attractions = gpsUtilService.getAttractions();
		return new ResponseEntity<Object>(attractions, HttpStatus.OK);
	}
	
	@GetMapping("/userlocation")
	public ResponseEntity<Object> getUserLocation(@RequestParam @NonNull UUID userId) 
			throws Exception {
		LOGGER.info("getUserLocation: userId=" + userId);
		VisitedLocation visitedLocation = gpsUtilService.getUserLocation(userId);
		return new ResponseEntity<Object>(visitedLocation, HttpStatus.OK);
	}

}
