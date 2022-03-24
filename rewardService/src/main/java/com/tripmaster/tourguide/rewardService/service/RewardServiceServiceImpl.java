/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.rewardService.converterDTO.IRewardConverterDTO;
import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
import com.tripmaster.tourguide.rewardService.remoteServices.IGpsService;
import com.tripmaster.tourguide.rewardService.remoteServices.IUserService;
import com.tripmaster.tourguide.rewardService.repository.IRewardRepository;
import com.tripmaster.tourguide.rewardService.util.IHelper;

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
	
	@Autowired
	private IRewardConverterDTO rewardConverter;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGpsService gpsService;
	
	@Autowired
	private IHelper helper;
	
	@Value("${reward.proximityBuffer:10.0}")
	private double proximityBuffer;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateRewards(UUID userId) throws HttpException {
		LOGGER.debug("calculateRewards: userId=" + userId);
		
		//List<VisitedLocation> visitedLocations = gpsService.getUserVisitedLocations(userId);
		VisitedLocation vl = new VisitedLocation(userId, new Location(10d, 20d), new Date());
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(vl);
		List<Attraction> attractions = gpsService.getAttractions();
		
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		List<Reward> rewards = (optional.isPresent() ? optional.get() : new ArrayList<>());
		
		Iterator<VisitedLocation> itVisitedLocation = visitedLocations.iterator();
		Iterator<Attraction> itAttraction = attractions.iterator();
		
		while(itVisitedLocation.hasNext()) {
			VisitedLocation visitedLocation = itVisitedLocation.next();
			while(itAttraction.hasNext()) {
				Attraction attraction = itAttraction.next();
				if(rewards.stream().filter(r -> 
							r.getAttraction().getAttractionName().equals(attraction.getAttractionName()))
						.count() == 0) {
					if(nearAttraction(visitedLocation.getLocation(), attraction)) {
						Reward reward = 
								new Reward(visitedLocation, attraction, 
										getRewardPoints(attraction.getAttractionId(), userId));
						rewardRepository.save(reward);
					}
				}
			}
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RewardDTO> getUserRewards(String userName) 
			throws UserNotFoundException, HttpException, ConverterException {
		LOGGER.debug("getUserRewards: userName=" + userName);
		
		UUID userId = userService.getUserId(userName);
		
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserRewards: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("user with userId=" + userId + " not found.");
		}
		
		LOGGER.debug("getUserRewards: returns " + optional.get().size() + " items.");
		
		return rewardConverter.converterRewardsToDTOs(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAttractionRewardPoints(UUID attractionId, UUID userId) {
		LOGGER.debug("getAttractionRewardPoints: attractionId=" + attractionId 
				+ ", userId=" + userId);
		
		return getRewardPoints(attractionId, userId);
	}

	private boolean nearAttraction(Location location, Attraction attraction) {
		double distance = helper.calculateDistance(location, attraction);
		return distance > proximityBuffer ? false : true;
	}

	private int getRewardPoints(UUID attractionId, UUID userId) {
		return rewardCentral.getAttractionRewardPoints(attractionId, userId);
	}
}
