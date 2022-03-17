/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpClientException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
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
	 * @param user NewUserDTO
	 * @return User
	 * @throws UserAlreadyExistsException 
	 * @throws ConverterException 
	 */
	User addUser(NewUserDTO user) throws UserAlreadyExistsException, ConverterException;
	
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
	 * @param preference NewPreferenceDTO
	 * @throws ConverterException 
	 */
	void updatePreferences(String userName, NewPreferenceDTO preference) throws UserNotFoundException, ConverterException;

	/**
	 * Get an user's trip deals.
	 * @param userName String
	 * @return List<Provider>
	 * @throws UserNotFoundException
	 * @throws HttpClientException 
	 */
	List<Provider> getTripDeals(String userName) throws UserNotFoundException, HttpClientException;
	
}
