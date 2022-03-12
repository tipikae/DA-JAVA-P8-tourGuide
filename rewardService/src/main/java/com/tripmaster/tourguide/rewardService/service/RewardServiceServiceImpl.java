/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rewardCentral.RewardCentral;

/**
 * RewardService service implementation.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class RewardServiceServiceImpl implements IRewardServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceServiceImpl.class);
	
	@Autowired
	private RewardCentral rewardCentral;

	

}
