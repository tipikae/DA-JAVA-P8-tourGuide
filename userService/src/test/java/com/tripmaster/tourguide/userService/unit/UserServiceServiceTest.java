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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.exceptions.UserAlreadyExistsException;
import com.tripmaster.tourguide.userService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.userService.repository.IUserServiceRepository;
import com.tripmaster.tourguide.userService.service.UserServiceServiceImpl;

import tripPricer.Provider;
import tripPricer.TripPricer;

@ExtendWith(MockitoExtension.class)
class UserServiceServiceTest {
	
	@Mock
	private IUserServiceRepository userRepository;
	
	@Mock
	private TripPricer tripPricer;
	
	@InjectMocks
	private UserServiceServiceImpl userService;
	
	private static User user;
	
	@BeforeAll
	private static void setUp() {
		ReflectionTestUtils.setField(UserServiceServiceImpl.class, "apiKey", "test-api-key");
		user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName("user");
		user.setEmailAddress("email");
		user.setNumberOfAdults(2);
		user.setNumberOfChildren(2);
		user.setTripDuration(7);
		user.setUserId(UUID.randomUUID().toString());
	}

	@Test
	void addUserReturnsUserWhenOk() throws UserAlreadyExistsException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertEquals("email", userService.addUser(user).getEmailAddress());
	}

	@Test
	void addUserThrowsExceptionWhenUserAlreadyExists() throws UserAlreadyExistsException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
	}
	
	@Test
	void getUserByUsernameReturnsUserWhenOk() throws UserNotFoundException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		assertEquals("user", userService.getUserByUsername("user").getUserName());
	}
	
	@Test
	void getUserByUsernameThrowsErrorWhenUserNotFound() throws UserNotFoundException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUserByUsername("user"));
	}
	
	@Test
	void getAllUsersReturnsNotEmptyListWhenOk() {
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(1, userService.getAllUsers().size());
	}
	
	@Test
	void getAllUsersReturnsEmptyListWhenEmpty() {
		when(userRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(userService.getAllUsers().isEmpty());
	}
	
	@Test
	void getRewardsReturnsNotEmptyListWhenOk() throws UserNotFoundException {
		List<Reward> rewards = new ArrayList<>();
		rewards.add(new Reward());
		user.setRewards(rewards);
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		assertEquals(1, userService.getRewards("user").size());
		
	}
	
	@Test
	void getRewardsThrowsErrorWhenUserNotFound() throws UserNotFoundException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getRewards("user"));
	}
	
	@Test
	void getVisitedLocationsReturnsNotEmptyListWhenOk() throws UserNotFoundException {
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(new VisitedLocation());
		user.setVisitedLocations(visitedLocations);
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		assertEquals(1, userService.getVisitedLocations("user").size());
		
	}
	
	@Test
	void getVisitedLocationsThrowsErrorWhenUserNotFound() throws UserNotFoundException {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getVisitedLocations("user"));
	}
	
	@Test
	void getTripDealsReturnsPricesWhenOk() throws UserNotFoundException {
		Reward reward = new Reward();
		reward.setRewardPoints(10);
		List<Reward> rewards = new ArrayList<>();
		rewards.add(reward);
		user.setRewards(rewards);
		List<Provider> providers = new ArrayList<>();
		providers.add(new Provider(UUID.randomUUID(), "provider1", 100));
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		when(tripPricer.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		assertEquals("provider1", userService.getTripDeals("user").get(0).name);
	}
	
	@Test
	void getTripDealsThrowsErrorWhenUserNotFound() {
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getTripDeals("user"));
	}

}
