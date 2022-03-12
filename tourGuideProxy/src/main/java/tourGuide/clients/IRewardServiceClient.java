/**
 * 
 */
package tourGuide.clients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/rewardservice/points", method = RequestMethod.GET)
	int getAttractionRewardPoints(
			@RequestParam("attractionId") UUID attractionId, 
			@RequestParam("userId") UUID userId);
}