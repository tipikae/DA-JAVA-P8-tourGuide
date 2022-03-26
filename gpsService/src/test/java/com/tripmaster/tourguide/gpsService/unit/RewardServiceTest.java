package com.tripmaster.tourguide.gpsService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.remoteServices.IRewardService;
import com.tripmaster.tourguide.gpsService.remoteServices.RewardServiceImpl;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {
	
	@Mock
	private IRewardServiceClient rewardClient;
	
	@InjectMocks
	private IRewardService rewardService = new RewardServiceImpl();

	@Test
	void getAttractionRewardPointsReturnsIntWhenOk() throws HttpException {
		int points = 100;
		when(rewardClient.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(points);
		assertEquals(points, rewardService.getAttractionRewardPoints(UUID.randomUUID(), UUID.randomUUID()));
	}

	@Test
	void getAttractionRewardPointsThrowsExceptionWhenFeignError() {
		doThrow(FeignException.class).when(rewardClient)
			.getAttractionRewardPoints(any(UUID.class), any(UUID.class));
		assertThrows(HttpException.class, () -> 
			rewardService.getAttractionRewardPoints(UUID.randomUUID(), UUID.randomUUID()));
	}

}
