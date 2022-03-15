/**
 * 
 */
package com.tripmaster.tourguide.rewardService.controller;

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

import com.tripmaster.tourguide.rewardService.dto.NewRewardDTO;
import com.tripmaster.tourguide.rewardService.model.Reward;
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
	 */
	@GetMapping("/calculate")
	public ResponseEntity<Object> calculate(@RequestParam @NotNull UUID userId) {
		return null;
	}
	
	/**
	 * Get az user's rewards.
	 * @param userId UUID
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/rewards")
	public ResponseEntity<Object> getUserRewards(@RequestParam @NotNull UUID userId) {
		return null;
	}
	
	/**
	 * Get a user's rewards points sum.
	 * @param userId UUID
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/points")
	public ResponseEntity<Object> getUserRewardsPoints(@RequestParam @NotNull UUID userId) {
		return null;
	}
	
}
