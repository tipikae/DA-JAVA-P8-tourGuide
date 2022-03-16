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
	
	@InjectMocks
	private UserServiceServiceImpl userService;
	
	private static User user;
	
	@BeforeAll
	private static void setUp() {
		ReflectionTestUtils.setField(UserServiceServiceImpl.class, "apiKey", "test-api-key");
		user = new User();
		user.setUserId(UUID.randomUUID());
		user.setEmailAddress("test@test.com");
		user.setUserName("test");
	}

}
