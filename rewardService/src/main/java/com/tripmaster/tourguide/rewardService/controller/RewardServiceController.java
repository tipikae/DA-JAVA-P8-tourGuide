/**
 * 
 */
package com.tripmaster.tourguide.rewardService.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.rewardService.service.IRewardServiceService;

/**
 * RewardCentral controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/rewardCentral")
public class RewardServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceController.class);
	
	@Autowired
	private IRewardServiceService rewardService;
	
	/**
	 * Get attraction reward points.
	 * @param attractionId
	 * @param userId
	 * @return ResponseEntity
	 */
	@GetMapping("/points")
	public ResponseEntity<Object> getAttractionRewardPoints(
			@RequestParam UUID attractionId,
			@RequestParam UUID userId) {
		LOGGER.info("getAttractionRewardPoints");
		return new ResponseEntity<Object>(
				rewardService.getAttractionRewardPoints(attractionId, userId), 
				HttpStatus.OK);
		
	}
}
