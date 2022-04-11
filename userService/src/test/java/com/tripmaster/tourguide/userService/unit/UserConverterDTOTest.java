package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.userService.converterDTO.IPreferenceConverterDTO;
import com.tripmaster.tourguide.userService.converterDTO.UserConverterDTOImpl;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.model.User;

@ExtendWith(MockitoExtension.class)
class UserConverterDTOTest {
	
	@Mock
	private IPreferenceConverterDTO preferenceConverter;
	
	@InjectMocks
	private UserConverterDTOImpl userConverterDTO;

	@Test
	void DTOconverterToEntityReturnsEntityWhenOk() throws ConverterException {
		NewUserDTO newUserDTO = new NewUserDTO();
		newUserDTO.setUserName("username");
		assertEquals("username", userConverterDTO.converterDTOToEntity(newUserDTO).getUserName());
	}
	
	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		User user = new User();
		user.setEmailAddress("email");
		assertEquals(user.getEmailAddress(), userConverterDTO.converterEntityToDTO(user).getEmailAddress());
	}
	
	@Test
	void converterEntitiesToDTOsReturnsListWhenOk() throws ConverterException {
		User user = new User();
		user.setUserName("username");
		List<User> users = new ArrayList<>();
		users.add(user);
		assertEquals(1, userConverterDTO.converterEntitiesToDTOs(users).size());
		assertEquals(user.getUserName(), userConverterDTO.converterEntitiesToDTOs(users).get(0).getUserName());
	}

}
