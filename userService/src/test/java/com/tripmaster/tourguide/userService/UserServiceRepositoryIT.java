package com.tripmaster.tourguide.userService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

@SpringBootTest
class UserServiceRepositoryIT {
	
	@Autowired
	private IUserRepository userRepository;

	@Test
	void test() {
		User user = new User();
		user.setUserId(UUID.randomUUID());
		user.setEmailAddress("test@test.com");
		user.setUserName("test");
		
		// save
		user = userRepository.save(user);
		assertNotNull(user.getUserId());
		assertEquals("test", user.getUserName());
		
		// find all
		List<User> users = userRepository.findAll();
		assertTrue(users.size() > 0);
		
		// find by username
		String username = user.getUserName();
		Optional<User> optional = userRepository.findByUsername(username);
		assertTrue(optional.isPresent());
	}

}
