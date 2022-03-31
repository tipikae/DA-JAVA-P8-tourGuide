/**
 * 
 */
package com.tripmaster.tourguide.rewardService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.rewardService.converterDTO.IAttractionConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.IRewardConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.IVisitedLocationConverterDTO;
import com.tripmaster.tourguide.rewardService.dto.NewVisitedLocationsAndAttractionsDTO;
import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
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
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	@Autowired
	private IRewardRepository rewardRepository;
	
	@Autowired
	private RewardCentral rewardCentral;
	
	@Autowired
	private IRewardConverterDTO rewardConverterDTO;
	
	@Autowired
	private IAttractionConverterDTO attractionConverterDTO;
	
	@Autowired
	private IVisitedLocationConverterDTO visitedLocationConverterDTO;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IHelper helper;
	
	@Value("${reward.proximityBuffer:10.0}")
	public double proximityBuffer;
	
	public RewardServiceServiceImpl() {
		addShutDownHook();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateRewards(UUID userId, NewVisitedLocationsAndAttractionsDTO 
			newVisitedLocationsAndAttractionsDTO) throws HttpException, ConverterException {
		LOGGER.debug("calculateRewards: userId=" + userId);

		List<VisitedLocation> visitedLocations = 
				visitedLocationConverterDTO.convertDTOsToEntities(
						newVisitedLocationsAndAttractionsDTO.getVisitedLocations());
		List<Attraction> attractions = 
				attractionConverterDTO.convertDTOsToEntities(
						newVisitedLocationsAndAttractionsDTO.getAttractions());
		
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		List<Reward> rewards = (optional.isPresent() ? optional.get() : new ArrayList<>());
		
		visitedLocations.parallelStream().forEach(visitedLocation -> {
			attractions.parallelStream().forEach(attraction -> {
				CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
					if(rewards.stream().filter(r -> 
								r.getAttraction().getAttractionName()
									.equals(attraction.getAttractionName()))
									.count() == 0) {
						if(nearAttraction(visitedLocation.getLocation(), attraction)) {
							Reward reward = 
									new Reward(visitedLocation, attraction, 
											getRewardPoints(attraction.getAttractionId(), userId));
							rewardRepository.save(reward);
						}
					}
				}, executorService);
				
				try {
					future.get();
				} catch (InterruptedException | ExecutionException e) {
					LOGGER.error("calculateRewards: get future error: " + e.getMessage());
					throw new CompletionException(e);
				}
			});
		});
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
		
		return rewardConverterDTO.converterRewardsToDTOs(optional.get());
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
	
	private void addShutDownHook() {
		LOGGER.debug("calculateRewards: addShutDownHook");
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				LOGGER.debug("calculateRewards: stopTracking");
				executorService.shutdownNow();
		      } 
		    }); 
	}
}
