/**
 * 
 */
package tourGuide.clients;

import java.util.List;

import javax.validation.constraints.NotBlank;

import feign.Param;
import feign.RequestLine;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;
import tourGuide.model.Reward;

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
	 * @return List
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /rewards/{userName}")
	List<Reward> getUserRewards(@Param("userName") @NotBlank String userName) 
			throws NotFoundException, BadRequestException, HttpException;
}
