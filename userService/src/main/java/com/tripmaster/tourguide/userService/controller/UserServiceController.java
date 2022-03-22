/**
 * 
 */
package com.tripmaster.tourguide.userService.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpClientException;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.service.IUserServiceService;

import tripPricer.Provider;

/**
 * User Service controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/userservice")
public class UserServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	private IUserServiceService userService;
	
	/**
	 * Add a user.
	 * @param newUserDTO NewUserDTO
	 * @return ResponseEntity<Object>
	 * @throws ConverterException 
	 * @throws UserAlreadyExistsException 
	 */
	@PostMapping(value = "/user", consumes = {"application/json"})
	public ResponseEntity<Object> addUser(@RequestBody @Valid NewUserDTO newUserDTO) 
			throws UserAlreadyExistsException, ConverterException {
		LOGGER.info("addUser");
		UserDTO userDTO = userService.addUser(newUserDTO);
		return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Get a user.
	 * @param userName String
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException 
	 * @throws ConverterException 
	 */
	@GetMapping("/user/{userName}")
	public ResponseEntity<Object> getUser(@PathVariable("userName") @NotBlank String userName) 
			throws UserNotFoundException, ConverterException {
		LOGGER.info("getUser");
		UserDTO userDTO = userService.getUser(userName);
		return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
	}
	
	/**
	 * Get all users.
	 * @return ResponseEntity<Object>
	 * @throws ConverterException 
	 */
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers() throws ConverterException {
		LOGGER.info("getAllUsers");
		List<UserDTO> userDTOs = userService.getAllUsers();
		return new ResponseEntity<Object>(userDTOs, HttpStatus.OK);
	}
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param newPreferenceDTO NewPreferenceDTO
	 * @return ResponseEntity<Object>
	 * @throws ConverterException 
	 * @throws UserNotFoundException 
	 */
	@PutMapping("/user/{userName}")
	public ResponseEntity<Object> updatePreferences(
			@PathVariable("userName") @NotBlank String userName,
			@RequestBody @Valid NewPreferenceDTO newPreferenceDTO) 
					throws UserNotFoundException, ConverterException {
		LOGGER.info("updatePreferences");
		userService.updatePreferences(userName, newPreferenceDTO);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	/**
	 * Get an user's trip deals.
	 * @param username String
	 * @return ResponseEntity<Object>
	 * @throws HttpClientException 
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/trips/{username}")
	public ResponseEntity<Object> getTripDeals(@PathVariable("username") @NotBlank String username) 
			throws UserNotFoundException, HttpException {
		LOGGER.info("getTripDeals");
		List<Provider> tripDeals = userService.getTripDeals(username);
		return new ResponseEntity<Object>(tripDeals, HttpStatus.OK);
	}
	
	/**
	 * Get an user's id.
	 * @param userName String
	 * @return ResponseEntity<Object>
	 * @throws UserNotFoundException
	 */
	@GetMapping("/user")
	public ResponseEntity<Object> getUserId(@RequestParam("userName") @NotBlank String userName) 
			throws UserNotFoundException {
		LOGGER.info("getUserId");
		UUID userId = userService.getUserId(userName);
		return new ResponseEntity<Object>(userId, HttpStatus.OK);
	}

}
