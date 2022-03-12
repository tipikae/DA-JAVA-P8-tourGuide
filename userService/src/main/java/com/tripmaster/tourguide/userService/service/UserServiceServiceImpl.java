/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.repository.IUserServiceRepository;

import tripPricer.Provider;
import tripPricer.TripPricer;

/**
 * UserService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class UserServiceServiceImpl implements IUserServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceServiceImpl.class);
	
	@Autowired
	private IUserServiceRepository userRepository;
	
	@Autowired
	private TripPricer tripPricer;
	
	@Value("${trippricer.apikey}")
	private static String apiKey;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reward> getUserRewards(String username) throws UserNotFoundException {
		LOGGER.debug("getRewards: username=" + username);
		Optional<User> optional = userRepository.findByUserName(username);
		if (!optional.isPresent()) {
			LOGGER.debug("getRewards: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		}
		
		return optional.get().getRewards();
	}

	
/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Provider> getTripDeals(String username) throws UserNotFoundException {
		LOGGER.debug("getTripDeals: username=" + username);
		Optional<User> optional = userRepository.findByUserName(username);
		if (!optional.isPresent()) {
			LOGGER.debug("getTripDeals: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		}
		
		User user = optional.get();
		
		return tripPricer.getPrice(apiKey, UUID.fromString(user.getUserId()), user.getNumberOfAdults(), 
				user.getNumberOfChildren(), user.getTripDuration(), getRewardsPoints(user));
	}

	private int getRewardsPoints(User user) {
		return user.getRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
	}
	
	

}
