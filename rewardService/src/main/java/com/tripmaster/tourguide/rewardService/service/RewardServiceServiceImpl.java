/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.repository.IRewardRepository;

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
	private IRewardRepository rewardRepository;
	
	@Autowired
	private RewardCentral rewardCentral;

	@Override
	public void calculateRewards(UUID userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reward> getUserRewards(UUID userId) throws UserNotFoundException {
		LOGGER.debug("getUserRewards: userId=" + userId);
		
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserRewards: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("user with userId=" + userId + " not found.");
		}
		
		return optional.get();
	}

	@Override
	public Reward addUserReward(Reward reward) {
		LOGGER.debug("addUserReward");
		return rewardRepository.save(reward);
	}

	@Override
	public int getUserRewardsPoints(UUID userId) throws UserNotFoundException {
		LOGGER.debug("getUserRewardsPoints: userId=" + userId);
		
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserRewardsPoints: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("user with userId=" + userId + " not found.");
		}
		
		int sum = 0;
		List<Reward> rewards = optional.get();
		for(Reward reward: rewards) {
			sum += reward.getRewardPoints();
		}
		
		return sum;
	}

	

}
