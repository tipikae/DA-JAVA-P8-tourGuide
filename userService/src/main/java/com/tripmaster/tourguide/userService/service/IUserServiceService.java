/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.List;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.exceptions.BadParametersException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;

/**
 * UserService service interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserServiceService {

////////////////////////////////////////////////////////////////////////////////////////////////////
// CRUD operations
////////////////////////////////////////////////////////////////////////////////////////////////////

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
	 * Update a user.
	 * @param username
	 * @param user
	 * @throws UserNotFoundException
	 * @throws BadParametersException 
	 */
	void updateUser(String username, User user) throws UserNotFoundException, BadParametersException;
	
	/**
	 * Delete a user.
	 * @param username
	 * @throws UserNotFoundException
	 */
	void deleteUser(String username) throws UserNotFoundException;


////////////////////////////////////////////////////////////////////////////////////////////////////
// Others operations
////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
}
