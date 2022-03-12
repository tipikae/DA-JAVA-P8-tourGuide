package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.service.RewardServiceServiceImpl;

import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
class RewardServiceServiceTest {
	
	@Mock
	private RewardCentral rewardCentral;
	
	@InjectMocks
	private RewardServiceServiceImpl rewardService;

}
