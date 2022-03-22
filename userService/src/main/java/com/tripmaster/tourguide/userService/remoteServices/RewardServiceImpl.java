/**
 * 
 */
package com.tripmaster.tourguide.userService.remoteServices;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.exceptions.HttpException;

/**
 * Reward Service.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class RewardServiceImpl implements IRewardService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);
	
	@Autowired
	private IRewardServiceClient rewardClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getUserRewardsPoints(UUID userId) throws HttpException {
		int points = 0;
		
		try {
			points = rewardClient.getUserRewardsPoints(userId);
		} catch (Exception e) {
			LOGGER.debug("getUserRewardsPoints: rewardClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return points;
	}

}
