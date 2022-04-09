/**
 * 
 */
package tourGuide.clients;

import javax.validation.constraints.NotBlank;

import feign.Param;
import feign.RequestLine;
import tourGuide.exception.HttpException;

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
	 * @throws HttpException
	 */
	@RequestLine("GET /rewards/{userName}")
	Object getUserRewards(@Param("userName") @NotBlank String userName) throws HttpException;
}
