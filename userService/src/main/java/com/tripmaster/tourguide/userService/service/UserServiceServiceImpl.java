/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.converterDTO.IPreferenceConverterDTO;
import com.tripmaster.tourguide.userService.converterDTO.IUserConverterDTO;
import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.model.Preference;
import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

import tripPricer.Provider;
import tripPricer.TripPricer;

/**
 * UserService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class UserServiceServiceImpl implements IUserServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceServiceImpl.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private TripPricer tripPricer;
	
	@Autowired
	private IRewardServiceClient rewardService;
	
	@Autowired
	private IUserConverterDTO userConverter;
	
	@Autowired
	private IPreferenceConverterDTO preferenceConverter;
	
	@Value("${trippricer.apikey:test-api-key}")
	private String apiKey;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDTO addUser(NewUserDTO newUserDTO) throws UserAlreadyExistsException, ConverterException {
		LOGGER.debug("addUser: username=" + newUserDTO.getUserName());
		
		Optional<User> optional = userRepository.findByUsername(newUserDTO.getUserName());
		if(optional.isPresent()) {
			LOGGER.debug("addUser: error: user with username=" + newUserDTO.getUserName() + " already exists.");
			throw new UserAlreadyExistsException(
					"user with username=" + newUserDTO.getUserName() + " already exists.");
		}
		
		User user = userConverter.converterDTOToEntity(newUserDTO);
		
		return userConverter.converterEntityToDTO(userRepository.save(user));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDTO getUser(String username) throws UserNotFoundException, ConverterException {
		LOGGER.debug("getUser: username=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("getUser: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		return userConverter.converterEntityToDTO(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserDTO> getAllUsers() throws ConverterException {
		LOGGER.debug("getAllUsers");
		
		List<UserDTO> userDTOs = userConverter.converterEntitiesToDTOs(userRepository.findAll());
		LOGGER.debug("getAllUsers: returns " + userDTOs.size() + " items.");
		
		return userDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePreferences(String username, NewPreferenceDTO newPreferenceDTO) 
			throws UserNotFoundException, ConverterException {
		LOGGER.debug("updatePreferences: username=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("updatePreferences: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		Preference preference = preferenceConverter.converterDTOToEntity(newPreferenceDTO);
		User user = optional.get();
		user.setPreference(preference);
		userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Provider> getTripDeals(String username) throws UserNotFoundException, HttpException {
		LOGGER.debug("getTripDeals: username=" + username);
		
		Optional<User> optional = userRepository.findByUsername(username);
		if(!optional.isPresent()) {
			LOGGER.debug("getTripDeals: error: user with username=" + username + " not found.");
			throw new UserNotFoundException(
					"user with username=" + username + " not found.");
		}
		
		User user = optional.get();
		Preference preference = user.getPreference();
		int points = rewardService.getUserRewardsPoints(user.getUserId());
		List<Provider> providers = tripPricer.getPrice(username, user.getUserId(), preference.getNumberOfAdults(), 
				preference.getNumberOfChildren(), preference.getTripDuration(), points);
		LOGGER.debug("getTripDeals: returns " + providers.size() + " items.");
		
		return providers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID getUserId(String userName) throws UserNotFoundException {
		LOGGER.debug("getUserId: username=" + userName);
		
		Optional<User> optional = userRepository.findByUsername(userName);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserId: error: user with username=" + userName + " not found.");
			throw new UserNotFoundException(
					"user with username=" + userName + " not found.");
		}
		UUID userId = optional.get().getUserId();
		LOGGER.debug(userName + " => " + userId);
		
		return userId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UUID> getAllUserIds() {
		LOGGER.debug("getAllUserIds");
		List<UUID> userIds = new ArrayList<>();
		userRepository.findAll().forEach(user -> userIds.add(user.getUserId()));
		
		return userIds;
	}

}
