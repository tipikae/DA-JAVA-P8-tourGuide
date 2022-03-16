/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;

import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.model.Preference;
import com.tripmaster.tourguide.userService.model.User;

import tripPricer.Provider;

/**
 * UserService service interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserServiceService {
	
	/**
	 * Add a user.
	 * @param user User
	 * @return User
	 * @throws UserAlreadyExistsException 
	 */
	User addUser(User user) throws UserAlreadyExistsException;
	
	/**
	 * Get a user by username.
	 * @param username String
	 * @return User
	 * @throws UserNotFoundException
	 */
	User getUser(String username) throws UserNotFoundException;
	
	/**
	 * Get all users.
	 * @return List<User>
	 */
	List<User> getAllUsers();
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param preference Preference
	 */
	void updatePreferences(String userName, Preference preference) throws UserNotFoundException;

	/**
	 * Get an user's trip deals.
	 * @param userName String
	 * @return List<Provider>
	 * @throws UserNotFoundException
	 */
	List<Provider> getTripDeals(String userName) throws UserNotFoundException;
	
}
