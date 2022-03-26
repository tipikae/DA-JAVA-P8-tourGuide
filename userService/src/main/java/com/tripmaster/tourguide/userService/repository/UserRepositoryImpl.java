/**
 * 
 */
package com.tripmaster.tourguide.userService.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tripmaster.tourguide.userService.model.User;

/**
 * User repository.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	public static Map<String, User> internalUsers = new HashMap<>();

	@Override
	public User save(User user) {
		LOGGER.debug("save: username=" + user.getUserName());
		
		if(internalUsers.containsKey(user.getUserName())) {
			internalUsers.replace(user.getUserName(), user);
		} else {
			internalUsers.put(user.getUserName(), user);
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		LOGGER.debug("findAll");
		List<User> users = new ArrayList<>(internalUsers.values());
		return users;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		LOGGER.debug("findByUsername: username=" + username);
		
		if(internalUsers.containsKey(username)) {
			return Optional.of(internalUsers.get(username));
		}
		
		return Optional.empty();
	}

}
