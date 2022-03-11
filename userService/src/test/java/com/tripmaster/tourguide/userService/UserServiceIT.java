package com.tripmaster.tourguide.userService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tripmaster.tourguide.userService.entities.Reward;
import com.tripmaster.tourguide.userService.entities.User;
import com.tripmaster.tourguide.userService.entities.VisitedLocation;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

@SpringBootTest("eureka.client.enabled:false")
class UserServiceIT {
	
	@Autowired
	private IUserRepository userRepository;

	@Transactional
	@Test
	void test() {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setEmailAddress("test@test.com");
		user.setUserName("test");
		
		// save
		user = userRepository.save(user);
		assertNotNull(user.getId());
		assertEquals("test", user.getUserName());
		
		VisitedLocation visitedLocation = new VisitedLocation();
		visitedLocation.setLatitude(10d);
		visitedLocation.setLongitude(20d);
		visitedLocation.setTimeVisited(new Date());
		visitedLocation.setUser(user);
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		Reward reward = new Reward();
		reward.setVisitedLocation(visitedLocation);
		reward.setRewardPoints(100);
		reward.setAttractionId(UUID.randomUUID().toString());
		reward.setAttractionName("attractionName");
		reward.setAttractionCity("cityTest");
		reward.setUser(user);
		List<Reward> rewards = new ArrayList<>();
		rewards.add(reward);

		// update
		user.setVisitedLocations(visitedLocations);
		user.setRewards(rewards);
		user = userRepository.save(user);
		assertEquals(10d, user.getVisitedLocations().get(0).getLatitude());
		assertEquals(20d, user.getRewards().get(0).getVisitedLocation().getLongitude());
		
		// find all
		List<User> users = userRepository.findAll();
		assertTrue(users.size() > 0);
		
		// find by username
		String username = user.getUserName();
		Optional<User> optional = userRepository.findByUserName(username);
		assertTrue(optional.isPresent());
		
		// delete
		userRepository.delete(user);
		optional = userRepository.findByUserName(username);
		assertFalse(optional.isPresent());
	}

}
