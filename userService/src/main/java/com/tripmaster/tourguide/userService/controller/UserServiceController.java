/**
 * 
 */
package com.tripmaster.tourguide.userService.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.service.IUserServiceService;

/**
 * User Service controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
public class UserServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	private IUserServiceService userService;
	
	/**
	 * Add a user.
	 * @param newUserDTO NewUserDTO
	 * @return ResponseEntity<Object>
	 */
	@PostMapping(value = "/add", consumes = {"application/json"})
	public ResponseEntity<Object> addUser(@RequestBody @Valid NewUserDTO newUserDTO) {
		return null;
	}
	
	/**
	 * Get a user.
	 * @param userId String
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable("userId") @NotNull String userId) {
		return null;
	}
	
	/**
	 * Get all users.
	 * @param userId String
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(@PathVariable("userId") @NotNull String userId) {
		return null;
	}
	
	/**
	 * Update an user's preferences.
	 * @param userId String
	 * @param newPreferenceDTO NewPreferenceDTO
	 * @return ResponseEntity<Object>
	 */
	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updatePreferences(
			@PathVariable("userId") @NotNull String userId,
			@RequestBody @Valid NewPreferenceDTO newPreferenceDTO) {
		return null;
	}
	
	/**
	 * Get an user's trip deals.
	 * @param username String
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/trips/{userId}")
	public ResponseEntity<Object> getTripDeals(@PathVariable("userId") @NotNull String username) {
		return null;
	}

}
