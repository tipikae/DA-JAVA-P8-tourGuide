package com.tripmaster.tourguide.userService.unit;

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

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.exceptions.HttpException;
import com.tripmaster.tourguide.userService.remoteServices.IRewardService;
import com.tripmaster.tourguide.userService.remoteServices.RewardServiceImpl;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {
	
	@Mock
	private IRewardServiceClient rewardClient;
	
	@InjectMocks
	private IRewardService rewardService = new RewardServiceImpl();

	@Test
	void getUserRewardsPointsReturnsSumWhenOk() throws HttpException {
		when(rewardClient.getUserRewardsPoints(any(UUID.class))).thenReturn(100);
		assertEquals(100, rewardService.getUserRewardsPoints(UUID.randomUUID()));

	}

	@Test
	void getUserRewardsPointsThrowsExceptionWhenFeignError() {
		doThrow(FeignException.class).when(rewardClient).getUserRewardsPoints(any(UUID.class));
		assertThrows(HttpException.class, () -> rewardService.getUserRewardsPoints(UUID.randomUUID()));

	}

}
