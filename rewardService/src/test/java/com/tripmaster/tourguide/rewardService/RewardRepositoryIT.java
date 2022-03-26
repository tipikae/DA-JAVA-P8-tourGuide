package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
import com.tripmaster.tourguide.rewardService.repository.IRewardRepository;

@SpringBootTest
class RewardRepositoryIT {
	
	@Autowired
	private IRewardRepository rewardRepository;

	@Test
	void test() {
		UUID userId = UUID.randomUUID();
		UUID attractionId = UUID.randomUUID();
		String attractionName = "attractionName";
		String city = "city";
		String state = "state";
		double latitude = 10d;
		double longitude = 20d;
		Attraction attraction = new Attraction(attractionId, attractionName, city, state, latitude, longitude);
		Location location = new Location(latitude, longitude);
		VisitedLocation visitedLocation = new VisitedLocation(userId, location, new Date());
		int points = 100;
		Reward reward = new Reward(visitedLocation, attraction, points);
		List<Reward> rewards = new ArrayList<>();
		rewards.add(reward);
		
		// save
		Reward saved = rewardRepository.save(reward);
		assertEquals(saved.getAttraction().getCity(), reward.getAttraction().getCity());
		
		// findByUserId
		Optional<List<Reward>> optional = rewardRepository.findByUserId(userId);
		assertTrue(optional.isPresent());
		assertEquals(userId, optional.get().get(0).getVisitedLocation().getUserId());
	}

}
