/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.model.Preference;
import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

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
	private IUserRepository userRepository;
	
	@Autowired
	private TripPricer tripPricer;
	
	@Autowired
	private IRewardServiceClient rewardClient;
	
	@Value("${trippricer.apikey}")
	private static String apiKey;

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		LOGGER.debug("save: username=" + user.getUserName());
		
		Optional<User> optional = userRepository.findByUsername(user.getUserName());
		if(optional.isPresent()) {
			LOGGER.debug("save: error: user with username=" + user.getUserName() + " already exists.");
			throw new UserAlreadyExistsException(
					"user with username=" + user.getUserName() + " already exists.");
		}
		
		return userRepository.save(user);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		LOGGER.debug("getUser: username=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("getUser: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		return optional.get();
	}

	@Override
	public List<User> getAllUsers() {
		LOGGER.debug("getAllUsers");
		return userRepository.findAll();
	}

	@Override
	public void updatePreferences(String username, Preference preference) throws UserNotFoundException {
		LOGGER.debug("updatePreferences: username=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("updatePreferences: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		User user = optional.get();
		user.setPreference(preference);
		userRepository.save(user);
	}

	@Override
	public List<Provider> getTripDeals(String username) throws UserNotFoundException {
		LOGGER.debug("getTripDeals: user,ame=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("updatePreferences: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		User user = optional.get();
		Preference preference = user.getPreference();
		int points = rewardClient.getUserRewardsPoints(user.getUserId());
		
		return tripPricer.getPrice(username, user.getUserId(), preference.getNumberOfAdults(), 
				preference.getNumberOfChildren(), preference.getTripDuration(), points);
	}

}
