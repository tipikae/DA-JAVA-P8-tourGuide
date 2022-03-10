package com.tripmaster.tourguide.rewardsCentral;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardsCentral.service.RewardCentralServiceImpl;

import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
class RewardCentralServiceTest {
	
	@Mock
	private RewardCentral rewardCentral;
	
	@InjectMocks
	private RewardCentralServiceImpl rewardService;

	@Test
	void getAttractionRewardPointsReturnsIntWhenOk() {
		int ret = 10;
		when(rewardCentral.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(ret);
		assertEquals(ret, rewardService.getAttractionRewardPoints(UUID.randomUUID(), UUID.randomUUID()));
	}

}
