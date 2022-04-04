/**
 * 
 */
package com.tripmaster.tourguide.userService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.model.User;

/**
 * User converter DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserConverterDTO extends IConverterDTO<User, UserDTO, NewUserDTO> {

	/**
	 * Convert an User list to UserDTO list.
	 * @param users List
	 * @return List
	 * @throws ConverterException
	 */
	List<UserDTO> converterEntitiesToDTOs(List<User> users) throws ConverterException;
}
