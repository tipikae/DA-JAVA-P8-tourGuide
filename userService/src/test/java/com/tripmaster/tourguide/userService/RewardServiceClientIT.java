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
		/*UUID userId = UUID.fromString("019b04a9-067a-4c76-8817-ee75088c3822");
		int points = rewardClient.getUserRewardsPoints(userId);
		assertEquals(100, points);*/
	}

}
