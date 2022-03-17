/**
 * 
 */
package com.tripmaster.tourguide.userService.converterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.userService.dto.NewUserDTO;
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

	@Override
	public User converterDTOToEntity(NewUserDTO dto) throws ConverterException {
		User user = new User();
		
		try {
			user.setEmailAddress(dto.getEmailAddress());
			user.setPhoneNumber(dto.getPhoneNumber());
			user.setUserId(dto.getUserId());
			user.setUserName(dto.getUserName());
		} catch (Exception e) {
			LOGGER.debug("converterDTOToEntity: error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return user;
	}

}
