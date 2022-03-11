/**
 * 
 */
package com.tripmaster.tourguide.userService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tripmaster.tourguide.userService.entities.User;

/**
 * User repository.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	/**
	 * Find a user by username.
	 * @param username
	 * @return Optional<User>
	 */
	Optional<User> findByUserName(String username);
}
