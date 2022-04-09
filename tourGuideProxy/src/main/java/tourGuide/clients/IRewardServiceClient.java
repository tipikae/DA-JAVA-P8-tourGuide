/**
 * 
 */
package tourGuide.clients;

import javax.validation.constraints.NotBlank;

import feign.FeignException;
import feign.Param;
import feign.RequestLine;

/**
 * Feign client for RewardService.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRewardServiceClient {

	/**
	 * Get an user's rewards.
	 * @param userName String
	 * @return Object
	 * @throws FeignException
	 */
	@RequestLine("GET /rewards/{userName}")
	Object getUserRewards(@Param("userName") @NotBlank String userName) throws FeignException;
}
