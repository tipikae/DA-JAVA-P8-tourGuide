package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.rewardService.util.HelperImpl;
import com.tripmaster.tourguide.rewardService.util.IHelper;

class HelperTest {
	
	private static IHelper helper = new HelperImpl();

	@Test
	void calculateDistance() {
		double distance = helper.calculateDistance(10d, 20d, 30d, 40d);
		assertTrue(distance != 0);
	}

}
