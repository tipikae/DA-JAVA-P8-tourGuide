/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.exceptions.BadParametersException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.repository.IUserServiceRepository;

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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		LOGGER.debug("addUser: username=" + user.getUserName());
		Optional<User> optional = userRepository.findByUserName(user.getUserName());
		if(optional.isPresent()) {
			LOGGER.debug("addUser: error: user with username=" + user.getUserName() + " already exists.");
			throw new UserAlreadyExistsException(
					"User with username=" + user.getUserName() + " already exists.");
		}
		
		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		LOGGER.debug("getUserByUsername: username=" + username);
		Optional<User> optional = userRepository.findByUserName(username);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserByUsername: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		}
		
		return optional.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getAllUsers() {
		LOGGER.debug("getAllUsers");
		return userRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @throws BadParametersException 
	 */
	@Override
	public void updateUser(String username, User updatedUser) throws UserNotFoundException, BadParametersException {
		LOGGER.debug("updateUser: username=" + username);
		if (!username.equals(updatedUser.getUserName())) {
			LOGGER.debug("updateUser: error: username=" + username 
					+ " and user.getUserName()=" + updatedUser.getUserName() + " mismatch.");
			throw new BadParametersException("username=" + username 
					+ " and user.getUserName()=" + updatedUser.getUserName() + " mismatch.");
		}
		
		Optional<User> optional = userRepository.findByUserName(username);
		if (!optional.isPresent()) {
			LOGGER.debug("updateUser: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		}
		
		User user = updateFields(optional.get(), updatedUser);
		userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(String username) throws UserNotFoundException {
		LOGGER.debug("deleteUser: username=" + username);
		Optional<User> optional = userRepository.findByUserName(username);
		if (!optional.isPresent()) {
			LOGGER.debug("deleteUser: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		} 
		userRepository.delete(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Reward> getRewards(String username) throws UserNotFoundException {
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
	public List<VisitedLocation> getVisitedLocations(String username) throws UserNotFoundException {
		LOGGER.debug("getVisitedLocations: username=" + username);
		Optional<User> optional = userRepository.findByUserName(username);
		if (!optional.isPresent()) {
			LOGGER.debug("getVisitedLocations: error: user with username=" + username + " does not exist.");
			throw new UserNotFoundException("user with username=" + username + " does not exist.");
		}
		
		return optional.get().getVisitedLocations();
	}
	
	private User updateFields(User user, User updatedUser) {
		user.setAttractionProximity(updatedUser.getAttractionProximity());
		user.setCurrency(updatedUser.getCurrency());
		user.setHighPricePoint(updatedUser.getHighPricePoint());
		user.setLatestLocationTimestamp(updatedUser.getLatestLocationTimestamp());
		user.setLowerPricePoint(updatedUser.getLowerPricePoint());
		user.setNumberOfAdults(updatedUser.getNumberOfAdults());
		user.setNumberOfChildren(updatedUser.getNumberOfChildren());
		user.setPhoneNumber(updatedUser.getPhoneNumber());
		user.setRewards(updatedUser.getRewards());
		user.setTicketQuantity(updatedUser.getTicketQuantity());
		user.setTripDuration(updatedUser.getTripDuration());
		user.setVisitedLocations(updatedUser.getVisitedLocations());
		
		return user;
	}

}
