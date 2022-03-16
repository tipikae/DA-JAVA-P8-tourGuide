package com.tripmaster.tourguide.userService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;

@SpringBootTest
class RewardServiceClientIT {
	
	@Autowired
	private IRewardServiceClient rewardClient;

	@Test
	void test() {
		//int points = rewardClient.getUserRewardsPoints(UUID.randomUUID());
		//assertEquals(0, points);
	}

}
