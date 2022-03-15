package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
import com.tripmaster.tourguide.rewardService.repository.IRewardRepository;
import com.tripmaster.tourguide.rewardService.service.RewardServiceServiceImpl;
import com.tripmaster.tourguide.rewardService.util.IHelper;

import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
class RewardServiceServiceTest {
	
	@Mock
	private IRewardRepository rewardRepository;
	
	@Mock
	private RewardCentral rewardCentral;
	
	@Mock
	private IHelper helper;
	
	@InjectMocks
	private RewardServiceServiceImpl rewardService;
	
	private static UUID userId;
	private static UUID attractionId;
	private static String attractionName;
	private static String city;
	private static String state;
	private static double latitude;
	private static double longitude;
	private static Attraction attraction;
	private static Location location;
	private static VisitedLocation visitedLocation;
	private static int points;
	private static Reward reward;
	private static List<Reward> rewards;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.randomUUID();
		attractionId = UUID.randomUUID();
		attractionName = "attractionName";
		city = "city";
		state = "state";
		latitude = 10d;
		longitude = 20d;
		attraction = new Attraction(attractionId, attractionName, city, state, latitude, longitude);
		location = new Location(latitude, longitude);
		visitedLocation = new VisitedLocation(userId, location, new Date());
		points = 100;
		reward = new Reward(visitedLocation, attraction, points);
		rewards = new ArrayList<>();
		rewards.add(reward);
	}
	
	@Test
	void calculateRewards() {
		
	}
	
	@Test
	void getUserRewardsReturnsListWhenOk() throws UserNotFoundException {
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(rewards));
		assertEquals(1, rewardService.getUserRewards(userId).size());
	}
	
	@Test
	void getUserRewardsThrowsExceptionWhenUserNotFound() {
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> rewardService.getUserRewards(userId));
	}
	
	@Test
	void getUserRewardsPointsReturnsSumWhenOk() throws UserNotFoundException {
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(rewards));
		assertEquals(points, rewardService.getUserRewardsPoints(userId));
	}
	
	@Test
	void getUserRewardsPointsThrowsExceptionWhenUserNotFound() {
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> rewardService.getUserRewardsPoints(userId));
	}
	
	@Test
	void getNearByAttractionsReturnsListWhenOk() {
		
	}
	
	@Test
	void getNearByAttractionsThrowsExceptionWhenUserNotFound() {
		
	}

}
