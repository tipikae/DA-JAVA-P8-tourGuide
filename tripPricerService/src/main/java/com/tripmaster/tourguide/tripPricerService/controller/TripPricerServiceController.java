/**
 * 
 */
package com.tripmaster.tourguide.tripPricerService.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.tripPricerService.service.ITripPricerServiceService;

/**
 * Trip Pricer controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/trippricer")
public class TripPricerServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TripPricerServiceController.class);
	
	@Autowired
	private ITripPricerServiceService tripPricerService;
	
	@GetMapping("/price")
	public ResponseEntity<Object> getPrice(@RequestParam UUID userId, @RequestParam int nbAdults, 
			@RequestParam int nbChildren, @RequestParam int tripDuration, 
			@RequestParam int rewardPoints) {
		LOGGER.info("getPrice: userId=" + userId + ", adults=" + nbAdults + ", children=" + nbChildren
				+ ", duration=" + tripDuration + ", rewardPoints=" + rewardPoints);
		
		return null;
	}

	@GetMapping("/provider")
	public ResponseEntity<Object> getProviderName(@RequestParam int adults) {
		LOGGER.info("getProviderName: adults=" + adults);
		
		return null;
	}
	
}
