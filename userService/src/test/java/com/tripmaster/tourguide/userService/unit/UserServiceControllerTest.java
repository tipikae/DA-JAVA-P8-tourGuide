package com.tripmaster.tourguide.userService.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.userService.controller.UserServiceController;
import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.dto.NewUserDTO;
import com.tripmaster.tourguide.userService.dto.UserDTO;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.service.IUserServiceService;

import tripPricer.Provider;

@WebMvcTest(UserServiceController.class)
class UserServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static String root;
	private static String username;
	private static UUID userId;
	private static String phone;
	private static String email;
	private static UserDTO userDTO;
	private static Provider provider;
	private static List<Provider> providers;
	private static List<UserDTO> userDTOs;
	
	@MockBean
	private IUserServiceService userService;
	
	@BeforeAll
	private static void setUp() {
		root = "/userservice";
		username = "username";
		userId = UUID.randomUUID();
		phone = "phone";
		email = "email";
		userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setUserName(username);
		userDTO.setPhoneNumber(phone);
		userDTO.setEmailAddress(email);
		userDTOs = new ArrayList<>();
		userDTOs.add(userDTO);
		provider = new Provider(UUID.randomUUID(), "name", 1000);
		providers = new ArrayList<>();
		providers.add(provider);
	}

	@Test
	void addUserReturnsOkWhenOk() throws Exception {
		when(userService.addUser(any(NewUserDTO.class))).thenReturn(userDTO);
		mockMvc.perform(post(root + "/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"" + userId + "\","
						+ "\"userName\": \"" + username + "\","
						+ "\"phoneNumber\": \"" + phone + "\","
						+ "\"emailAddress\": \"" + email + "\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}

	@Test
	void addUserReturns405WhenAlreadyExists() throws Exception {
		doThrow(UserAlreadyExistsException.class).when(userService).addUser(any(NewUserDTO.class));
		mockMvc.perform(post(root + "/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"" + userId + "\","
						+ "\"userName\": \"" + username + "\","
						+ "\"phoneNumber\": \"" + phone + "\","
						+ "\"emailAddress\": \"" + email + "\"}"))
			.andExpect(status().is(405));
	}

	@Test
	void addUserReturns400WhenInvalid() throws Exception {
		mockMvc.perform(post(root + "/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"\","
						+ "\"userName\": \"" + username + "\","
						+ "\"phoneNumber\": \"" + phone + "\","
						+ "\"emailAddress\": \"" + email + "\"}"))
			.andExpect(status().is(400));
	}
	
	@Test
	void getUserReturnsUserWhenOk() throws Exception {
		when(userService.getUser(username)).thenReturn(userDTO);
		mockMvc.perform(get(root + "/user/" + username))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}
	
	@Test
	void getUserReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(userService).getUser(anyString());
		mockMvc.perform(get(root + "/user/" + username))
			.andExpect(status().is(404));
		
	}
	
	@Test
	void getAllUsersReturnsListWhenOk() throws Exception {
		when(userService.getAllUsers()).thenReturn(userDTOs);
		mockMvc.perform(get(root + "/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].userName", is(username)));
	}
	
	@Test
	void updatePreferencesReturns200WhenOk() throws Exception {
		doNothing().when(userService).updatePreferences(anyString(), any(NewPreferenceDTO.class));
		mockMvc.perform(put(root + "/user/" + username)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tripDuration\": \"10\","
						+ "\"ticketQuantity\": \"4\","
						+ "\"numberOfAdults\": \"2\","
						+ "\"numberOfChildren\": \"2\"}"))
			.andExpect(status().isOk());
	}
	
	@Test
	void updatePreferencesReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class)
			.when(userService).updatePreferences(anyString(), any(NewPreferenceDTO.class));
		mockMvc.perform(put(root + "/user/" + username)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tripDuration\": \"10\","
						+ "\"ticketQuantity\": \"4\","
						+ "\"numberOfAdults\": \"2\","
						+ "\"numberOfChildren\": \"2\"}"))
			.andExpect(status().is(404));
	}
	
	@Test
	void updatePreferencesReturns400WhenInvalid() throws Exception {
		mockMvc.perform(put(root + "/user/" + username)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tripDuration\": \"-10\","
						+ "\"ticketQuantity\": \"4\","
						+ "\"numberOfAdults\": \"2\","
						+ "\"numberOfChildren\": \"2\"}"))
			.andExpect(status().is(400));
	}
	
	@Test
	void getTripDealsReturnsListWhenOk() throws Exception {
		when(userService.getTripDeals(anyString())).thenReturn(providers);
		mockMvc.perform(get(root + "/trips/" + username))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].price", is(1000.0)));
	}
	
	@Test
	void getTripDealsReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(userService).getTripDeals(anyString());
		mockMvc.perform(get(root + "/trips/" + username))
			.andExpect(status().is(404));
	}
	
	@Test
	void getUserIdReturnsUUIDWhenOk() throws Exception {
		when(userService.getUserId(anyString())).thenReturn(userId);
		mockMvc.perform(get(root + "/user")
				.param("userName", username))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", is(userId.toString())));
	}
	
	@Test
	void getUserIdReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(userService).getUserId(anyString());
		mockMvc.perform(get(root + "/user")
				.param("userName", username))
			.andExpect(status().is(404));
	}
	
}
