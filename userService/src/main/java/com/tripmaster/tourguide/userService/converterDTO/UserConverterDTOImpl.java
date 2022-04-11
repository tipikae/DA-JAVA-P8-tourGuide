/**
 * 
 */
package com.tripmaster.tourguide.userService.converterDTO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.model.User;

/**
 * User converter DTO implementation.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class UserConverterDTOImpl implements IUserConverterDTO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserConverterDTOImpl.class);
	
	@Autowired
	private IPreferenceConverterDTO preferenceConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User converterDTOToEntity(NewUserDTO dto) throws ConverterException {
		User user = new User();
		
		try {
			user.setEmailAddress(dto.getEmailAddress());
			user.setPhoneNumber(dto.getPhoneNumber());
			user.setUserId(dto.getUserId());
			user.setUserName(dto.getUserName());
		} catch (Exception e) {
			LOGGER.debug("converterDTOToEntity: exception: " + e.getClass().getSimpleName() + ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDTO converterEntityToDTO(User entity) throws ConverterException {
		UserDTO userDTO = new UserDTO();
		
		try {
			userDTO.setEmailAddress(entity.getEmailAddress());
			userDTO.setLatestLocationTimestamp(entity.getLatestLocationTimestamp());
			userDTO.setPhoneNumber(entity.getPhoneNumber());
			userDTO.setPreferenceDTO(preferenceConverter.converterEntityToDTO(entity.getPreference()));
			userDTO.setUserId(entity.getUserId());
			userDTO.setUserName(entity.getUserName());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() + ", message: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return userDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserDTO> converterEntitiesToDTOs(List<User> users) throws ConverterException {
		List<UserDTO> userDTOs = new ArrayList<>();
		
		for(User user: users) {
			userDTOs.add(converterEntityToDTO(user));
		}
		
		return userDTOs;
	}

}
