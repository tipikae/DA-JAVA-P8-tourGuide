/**
 * 
 */
package com.tripmaster.tourguide.userService.repository;

import java.util.List;
import java.util.Optional;

import com.tripmaster.tourguide.userService.model.User;

/**
 * User repository interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserRepository {
	
	/**
	 * Save a user.
	 * @param user User
	 * @return User
	 */
	User save(User user);
	
	/**
	 * Find all users.
	 * @return List
	 */
	List<User> findAll();

	/**
	 * Find a user by username.
	 * @param username String
	 * @return Optional
	 */
	Optional<User> findByUsername(String username);
}
