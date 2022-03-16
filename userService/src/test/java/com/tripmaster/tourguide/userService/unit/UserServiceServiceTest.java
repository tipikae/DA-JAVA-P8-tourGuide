package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.model.Preference;
import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;
import com.tripmaster.tourguide.userService.service.UserServiceServiceImpl;

import tripPricer.Provider;
import tripPricer.TripPricer;

@ExtendWith(MockitoExtension.class)
class UserServiceServiceTest {
	
	@Mock
	private IUserRepository userRepository;
	
	@Mock
	private TripPricer tripPricer;
	
	@Mock
	private IRewardServiceClient rewardClient;
	
	@InjectMocks
	private UserServiceServiceImpl userService;
	
	private static String username;
	private static User user;
	private static Provider provider;
	private static List<Provider> providers;
	private static List<User> users;
	
	@BeforeAll
	private static void setUp() {
		ReflectionTestUtils.setField(UserServiceServiceImpl.class, "apiKey", "test-api-key");
		username = "username";
		user = new User(UUID.randomUUID(), username, "phone", "email");
		users = new ArrayList<>();
		users.add(user);
		provider = new Provider(UUID.randomUUID(), "name", 1000);
		providers = new ArrayList<>();
		providers.add(provider);
	}
	
	@Test
	void addUserReturnsUserWhenOk() throws UserAlreadyExistsException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertEquals(username, userService.addUser(user).getUserName());
	}
	
	@Test
	void addUserThrowsExceptionWhenUserAlreadyExists() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
	}
	
	@Test
	void getUserReturnsUserWhenOk() throws UserNotFoundException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		assertEquals("email", userService.getUser(username).getEmailAddress());
	}
	
	@Test
	void getUserThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUser(username));
	}
	
	@Test
	void getAllUsersReturnsListWhenOk() {
		when(userRepository.findAll()).thenReturn(users);
		assertTrue(userService.getAllUsers().size() > 0);
	}
	
	@Test
	void updatePreferencesCallsSaveWhenOk() throws UserNotFoundException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		userService.updatePreferences(username, new Preference());
		Mockito.verify(userRepository).save(any(User.class));
	}
	
	@Test
	void updatePreferencesThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, 
				() -> userService.updatePreferences(username, new Preference()));
	}
	
	@Test
	void getTripDealsReturnsListWhenOk() throws UserNotFoundException {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
		when(rewardClient.getAttractionRewardPoints(any(UUID.class))).thenReturn(100);
		when(tripPricer.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		assertTrue(userService.getTripDeals(username).size() > 0);
		
	}
	
	@Test
	void getTripDealsThrowsExceptionWhenUserNotFound() {
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getTripDeals(username));
	}

}
