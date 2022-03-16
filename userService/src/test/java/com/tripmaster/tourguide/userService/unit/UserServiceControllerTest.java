package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.userService.controller.UserServiceController;
import com.tripmaster.tourguide.userService.service.IUserServiceService;

@WebMvcTest(UserServiceController.class)
class UserServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IUserServiceService userService;

	@Test
	void addUserReturnsOkWhenOk() {
		
	}

	@Test
	void addUserReturns405WhenAlreadyExists() {
		
	}
	
	@Test
	void getUserReturnsUserWhenOk() {
		
	}
	
	@Test
	void getUserReturns404WhenUserNotFound() {
		
	}
	
	@Test
	void getAllUsersReturnsListWhenOk() {
		
	}
	
	@Test
	void getAllUsersReturns404WhenUserNotFound() {
		
	}
	
	@Test
	void updatePreferencesCallsUpdateWhenOk() {
		
	}
	
	@Test
	void updatePreferencesReturns404WhenUserNotFound() {
		
	}
	
	@Test
	void getTripDealsReturnsListWhenOk() {
		
	}
	
	@Test
	void getTripDealsReturns404WhenUserNotFound() {
		
	}
	
}
