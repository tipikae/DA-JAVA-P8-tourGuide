/**
 * 
 */
package tourGuide.clients;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.FeignException;
import tourGuide.model.Reward;

/**
 * Feign client for RewardService.
 * @author tipikae
 * @version 1.0
 *
 */
@FeignClient(name = "RewardService", url = "http://localhost:8083/rewardservice")
public interface IRewardServiceClient {

	/**
	 * Get an user's rewards.
	 * @param userName String
	 * @return List<Reward>
	 * @throws FeignException
	 */
	@GetMapping("/rewards/{userName}")
	List<Reward> getUserRewards(@PathVariable("userName") @NotBlank String userName) throws FeignException;
}
