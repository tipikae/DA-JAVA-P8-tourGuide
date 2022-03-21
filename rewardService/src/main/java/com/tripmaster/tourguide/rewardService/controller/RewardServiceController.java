/**
 * 
 */
package com.tripmaster.tourguide.rewardService.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
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
	
	/**
	 * Calculate rewards.
	 * @param userId UUID
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/calculate")
	public ResponseEntity<Object> calculate(@RequestParam @NotNull UUID userId) 
			throws UserNotFoundException {
		LOGGER.info("calculate");
		rewardService.calculateRewards(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Get an user's rewards.
	 * @param userName String
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/rewards/{userName}")
	public ResponseEntity<Object> getUserRewards(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException {
		LOGGER.info("getUserRewards");
		List<RewardDTO> rewards = rewardService.getUserRewards(userName);
		return new ResponseEntity<Object>(rewards, HttpStatus.OK);
	}
	
	/**
	 * Get an user's rewards points sum.
	 * @param userId UUID
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/points/{userId}")
	public ResponseEntity<Object> getUserRewardsPoints(@PathVariable("userId") @NotNull UUID userId) 
			throws UserNotFoundException {
		LOGGER.info("getUserRewardsPoints");
		int points = rewardService.getUserRewardsPoints(userId);
		return new ResponseEntity<Object>(points, HttpStatus.OK);
	}
	
}
