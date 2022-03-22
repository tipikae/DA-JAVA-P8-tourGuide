/**
 * 
 */
package com.tripmaster.tourguide.gpsService.remoteServices;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;

/**
 * RewardService service.
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
	public int getAttractionRewardPoints(UUID attractionId, UUID userId) throws HttpException {
		int points = 0;
		
		try {
			points = rewardClient.getAttractionRewardPoints(attractionId, userId);
		} catch (Exception e) {
			LOGGER.debug("getAttractionRewardPoints: rewardClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return points;
	}

}
