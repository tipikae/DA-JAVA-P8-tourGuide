/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
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
	 * Add a user.
	 * @param user NewUserDTO
	 * @return UserDTO
	 * @throws UserAlreadyExistsException 
	 * @throws ConverterException 
	 */
	UserDTO addUser(NewUserDTO user) throws UserAlreadyExistsException, ConverterException;
	
	/**
	 * Get a user by username.
	 * @param username String
	 * @return UserDTO
	 * @throws UserNotFoundException
	 * @throws ConverterException 
	 */
	UserDTO getUser(String username) throws UserNotFoundException, ConverterException;
	
	/**
	 * Get all users.
	 * @return List
	 * @throws ConverterException 
	 */
	List<UserDTO> getAllUsers() throws ConverterException;
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param preference NewPreferenceDTO
	 * @throws ConverterException 
	 */
	void updatePreferences(String userName, NewPreferenceDTO preference) 
			throws UserNotFoundException, ConverterException;

	/**
	 * Get an user's trip deals.
	 * @param userName String
	 * @return List
	 * @throws UserNotFoundException
	 * @throws HttpException 
	 */
	List<Provider> getTripDeals(String userName) 
			throws UserNotFoundException, HttpException;
	
	/**
	 * Get an user's id.
	 * @param userName String
	 * @return UUID
	 * @throws UserNotFoundException
	 */
	UUID getUserId(String userName) throws UserNotFoundException;

	/**
	 * Get all userIds.
	 * @return List
	 */
	List<UUID> getAllUserIds();
	
}
