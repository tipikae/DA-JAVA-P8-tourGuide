/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;

import tripPricer.Provider;

/**
 * UserService service interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserServiceService {

	/**
	 * Add a new user.
	 * @param user
	 * @return User
	 * @throws UserAlreadyExistsException
	 */
	User addUser(User user) throws UserAlreadyExistsException;
	
	/**
	 * Get a user by his username.
	 * @param username
	 * @return User
	 * @throws UserNotFoundException
	 */
	User getUserByUsername(String username) throws UserNotFoundException;
	
	/**
	 * Get all users.
	 * @return List<User>
	 */
	List<User> getAllUsers();
	
	/**
	 * Get a user rewards.
	 * @param username
	 * @return List<Reward>
	 * @throws UserNotFoundException
	 */
	List<Reward> getRewards(String username) throws UserNotFoundException;
	
	/**
	 * Get a user visited locations.
	 * @param username
	 * @return List<VisitedLocation>
	 * @throws UserNotFoundException
	 */
	List<VisitedLocation> getVisitedLocations(String username) throws UserNotFoundException;
	
	/**
	 * Get trip deals according user preferences.
	 * @return List<Provider>
	 * @throws UserNotFoundException
	 */
	List<Provider> getTripDeals(String username) throws UserNotFoundException;
	
}
