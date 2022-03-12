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
	 * Get a user rewards.
	 * @param username
	 * @return List<Reward>
	 * @throws UserNotFoundException
	 */
	List<Reward> getUserRewards(String username) throws UserNotFoundException;
	
	/**
	 * Get trip deals according user preferences.
	 * @return List<Provider>
	 * @throws UserNotFoundException
	 */
	List<Provider> getTripDeals(String username) throws UserNotFoundException;
	
}
