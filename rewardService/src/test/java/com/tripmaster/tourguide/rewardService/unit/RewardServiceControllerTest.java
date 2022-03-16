package com.tripmaster.tourguide.rewardService.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.rewardService.controller.RewardServiceController;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
import com.tripmaster.tourguide.rewardService.service.IRewardServiceService;

@WebMvcTest(RewardServiceController.class)
class RewardServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IRewardServiceService rewardService;
	
	private static String root;
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
		root = "/rewardservice";
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
	void calculate() {
		
	}
	
	@Test
	void getUserRewardsReturnsListWhenOk() throws UserNotFoundException, Exception {
		when(rewardService.getUserRewards(any(UUID.class))).thenReturn(rewards);
		mockMvc.perform(get(root + "/rewards")
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].attraction.city", is(city)));
	}
	
	@Test
	void getUserRewardsReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(rewardService).getUserRewards(any(UUID.class));
		mockMvc.perform(get(root + "/rewards")
				.param("userId", userId.toString()))
			.andExpect(status().is(404));
	}
	
	@Test
	void getUserRewardsPointsReturnsIntWhenOk() throws Exception {
		when(rewardService.getUserRewardsPoints(any(UUID.class))).thenReturn(points);
		mockMvc.perform(get(root + "/points/" + userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", is(points)));
	}
	
	@Test
	void getUserRewardsPointsReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(rewardService).getUserRewardsPoints(any(UUID.class));
		mockMvc.perform(get(root + "/points/" + userId.toString()))
			.andExpect(status().is(404));
	}
	
	@Test
	void getNearByAttractionsReturnsListWhenOk() throws Exception {
		List<Attraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		when(rewardService.getNearByAttractions(any(UUID.class))).thenReturn(attractions);
		mockMvc.perform(get(root + "/nearbyattractions")
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].attractionId", is(attractionId.toString())));
	}
	
	@Test
	void getNearByAttractionsReturns404WhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(rewardService).getNearByAttractions(any(UUID.class));
		mockMvc.perform(get(root + "/nearbyattractions")
				.param("userId", userId.toString()))
			.andExpect(status().is(404));
	}

}
