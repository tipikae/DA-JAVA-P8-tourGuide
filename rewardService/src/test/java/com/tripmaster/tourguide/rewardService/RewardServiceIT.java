package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.service.IRewardServiceService;

@SpringBootTest
class RewardServiceIT {
	
	@Autowired
	private IRewardServiceService rewardService;
	
	private static UUID userId;
	private static String userName;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.fromString("7894208e-d299-4485-85dc-07f19308c1ea");
		userName = "jon";
	}

	@Test
	void calculateRewards() {
		fail("Not yet implemented");
	}

	@Test
	void getUserRewards() throws UserNotFoundException, HttpException, ConverterException {
		assertTrue(rewardService.getUserRewards(userName).size() > 0);
	}

	@Test
	void getUserRewardsPoints() throws UserNotFoundException {
		assertTrue(rewardService.getUserRewardsPoints(userId) > 0);
	}

	@Test
	void getAttractionRewardPoints() {
		assertTrue(rewardService.getAttractionRewardPoints(UUID.randomUUID(), userId) > 0);
	}

}
