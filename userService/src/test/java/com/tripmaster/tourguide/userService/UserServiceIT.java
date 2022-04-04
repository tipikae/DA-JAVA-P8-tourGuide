package com.tripmaster.tourguide.userService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.service.IUserServiceService;

@SpringBootTest
class UserServiceIT {
	
	@Autowired
	private IUserServiceService userService;
	
	private static NewUserDTO newUserDTO;
	private static UUID userId;
	private static String userName;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.fromString("7894208e-d299-4485-85dc-07f19308c1ea");
		userName = "jon";
		newUserDTO = new NewUserDTO(UUID.randomUUID(), "test", "000", "test@test.com");
	}

	@Order(1)
	@Test
	void addUser() throws UserAlreadyExistsException, ConverterException {
		assertEquals(newUserDTO.getUserName(), userService.addUser(newUserDTO).getUserName());
	}

	@Test
	void getUser() throws UserNotFoundException, ConverterException, UserAlreadyExistsException {
		assertEquals(userId, userService.getUser(userName).getUserId());
	}

	@Test
	void getAllUsers() throws UserAlreadyExistsException, ConverterException {
		assertTrue(userService.getAllUsers().size() > 0);
	}

	@Test
	void updatePreferences() throws UserAlreadyExistsException, ConverterException, UserNotFoundException {
		NewPreferenceDTO newPreferenceDTO = new NewPreferenceDTO();
		newPreferenceDTO.setTripDuration(7);
		newPreferenceDTO.setTicketQuantity(4);
		newPreferenceDTO.setNumberOfAdults(2);
		newPreferenceDTO.setNumberOfChildren(2);
		userService.updatePreferences(userName, newPreferenceDTO);
		assertEquals(7, userService.getUser(userName).getPreferenceDTO().getTripDuration());
	}

	@Test
	void getTripDeals() 
			throws UserAlreadyExistsException, ConverterException, UserNotFoundException, HttpException {
		assertTrue(userService.getTripDeals(userName).size() > 0);
	}

	@Test
	void getUserId() throws UserAlreadyExistsException, ConverterException, UserNotFoundException {
		assertEquals(userId, userService.getUserId(userName));
	}

	@Test
	void getAllUserIds() throws UserAlreadyExistsException, ConverterException {
		assertTrue(userService.getAllUserIds().size() > 0);
	}

}
