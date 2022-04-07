/**
 * 
 */
package com.tripmaster.tourguide.rewardService.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.rewardService.dto.NewVisitedLocationsAndAttractionsDTO;
import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
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
@Validated
public class RewardServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceController.class);

	@Autowired
	private IRewardServiceService rewardService;
	
	/**
	 * Calculate rewards.
	 * @param userId UUID
	 * @param newVisitedLocationsAndAttractionsDTO NewVisitedLocationsAndAttractionsDTO
	 * @return ResponseEntity
	 * @throws HttpException 
	 * @throws ConverterException
	 */
	@PostMapping(value = "/calculate/{userId}", consumes = {"application/json"})
	public ResponseEntity<Object> calculate(
			@PathVariable("userId") @NotNull UUID userId,
			@RequestBody @Valid NewVisitedLocationsAndAttractionsDTO newVisitedLocationsAndAttractionsDTO) 
			throws HttpException, ConverterException {
		LOGGER.info("calculate");
		rewardService.calculateRewards(userId, newVisitedLocationsAndAttractionsDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Get an user's rewards.
	 * @param userName String
	 * @return ResponseEntity
	 * @throws UserNotFoundException 
	 * @throws ConverterException 
	 * @throws HttpException 
	 */
	@GetMapping("/rewards/{userName}")
	public ResponseEntity<Object> getUserRewards(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException, HttpException, ConverterException {
		LOGGER.info("getUserRewards");
		List<RewardDTO> rewards = rewardService.getUserRewards(userName);
		return new ResponseEntity<Object>(rewards, HttpStatus.OK);
	}
	
	/**
	 * Get an user's rewards points sum.
	 * @param userId UUID
	 * @return ResponseEntity
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/points/{userId}")
	public ResponseEntity<Object> getUserRewardsPoints(@PathVariable("userId") @NotNull UUID userId) 
			throws UserNotFoundException {
		LOGGER.info("getUserRewardsPoints");
		int points = rewardService.getUserRewardsPoints(userId);
		return new ResponseEntity<Object>(points, HttpStatus.OK);
	}
	
	/**
	 * Get an attraction's reward points.
	 * @param attractionId UUID
	 * @param userId UUID
	 * @return ResponseEntity
	 */
	@GetMapping("/reward")
	public ResponseEntity<Object> getAttractionRewardPoints(
			@RequestParam("attractionId") @NotNull UUID attractionId, 
			@RequestParam("userId") @NotNull UUID userId) {
		LOGGER.info("getAttractionRewardPoints");
		int points = rewardService.getAttractionRewardPoints(attractionId, userId);
		return new ResponseEntity<Object>(points, HttpStatus.OK);
	}
	
}
