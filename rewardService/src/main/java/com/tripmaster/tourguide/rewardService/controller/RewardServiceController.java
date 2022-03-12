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
 * RewardService controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/rewardservice")
public class RewardServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceController.class);
	
	@Autowired
	private IRewardServiceService rewardService;
	
	
}
