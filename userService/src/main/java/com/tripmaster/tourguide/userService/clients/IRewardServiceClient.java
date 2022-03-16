/**
 * 
 */
package com.tripmaster.tourguide.userService.clients;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client for RewardService.
 * @author tipikae
 * @version 1.0
 *
 */
@FeignClient(url = "http://localhost:8083")
public interface IRewardServiceClient {

	/**
	 * Get attraction reward points.
	 * @param attractionId
	 * @param userId
	 * @return int
	 */
	@GetMapping("/rewardservice/points")
	int getAttractionRewardPoints(@RequestParam("userId") @NotNull UUID userId);
}
