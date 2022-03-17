package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.userService.converterDTO.IUserConverterDTO;
import com.tripmaster.tourguide.userService.converterDTO.UserConverterDTOImpl;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;

class UserConverterDTOTest {
	
	private IUserConverterDTO userConverterDTO = new UserConverterDTOImpl();

	@Test
	void DTOconverterToEntityReturnsEntityWhenOk() throws ConverterException {
		NewUserDTO newUserDTO = new NewUserDTO();
		newUserDTO.setUserName("username");
		assertEquals("username", userConverterDTO.converterDTOToEntity(newUserDTO).getUserName());
	}

}
