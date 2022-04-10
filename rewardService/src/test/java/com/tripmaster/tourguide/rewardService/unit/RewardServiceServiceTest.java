package com.tripmaster.tourguide.rewardService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.clients.IUserServiceClient;
import com.tripmaster.tourguide.rewardService.converterDTO.IAttractionConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.IRewardConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.IVisitedLocationConverterDTO;
import com.tripmaster.tourguide.rewardService.dto.AttractionDTO;
import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.dto.NewVisitedLocationsAndAttractionsDTO;
import com.tripmaster.tourguide.rewardService.dto.RewardDTO;
import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.User;
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
	private IRewardConverterDTO rewardConverterDTO;
	
	@Mock
	private IAttractionConverterDTO attractionConverterDTO;
	
	@Mock
	private IVisitedLocationConverterDTO visitedLocationConverterDTO;
	
	@Mock
	private IUserServiceClient userService;
	
	@Mock
	private IHelper helper;
	
	@InjectMocks
	private RewardServiceServiceImpl rewardService;
	
	private static String userName;
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
		userName = "username";
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
	void calculateRewardsWhenOk() throws HttpException, ConverterException {
		LocationDTO locationDTO = new LocationDTO(latitude, longitude);
		List<AttractionDTO> attractionDTOs = Arrays.asList(
				new AttractionDTO(attractionId, attractionName, city, state, latitude, longitude));
		List<VisitedLocationDTO> visitedLocationDTOs = Arrays.asList(
				new VisitedLocationDTO(userId, locationDTO, new Date()));
		NewVisitedLocationsAndAttractionsDTO visitedLocationsAndAttractionsDTO 
			= new NewVisitedLocationsAndAttractionsDTO();
		visitedLocationsAndAttractionsDTO.setAttractions(attractionDTOs);
		visitedLocationsAndAttractionsDTO.setVisitedLocations(visitedLocationDTOs);
		when(visitedLocationConverterDTO.convertDTOsToEntities(anyList()))
			.thenReturn(Arrays.asList(visitedLocation));
		when(attractionConverterDTO.convertDTOsToEntities(anyList()))
			.thenReturn(Arrays.asList(attraction));
		rewardService.calculateRewards(userId, visitedLocationsAndAttractionsDTO);
		Mockito.verify(rewardRepository).save(any(Reward.class));
	}
	
	@Test
	void getUserRewardsReturnsListWhenOk() throws UserNotFoundException, ConverterException, HttpException {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		List<RewardDTO> rewardDTOs = new ArrayList<>();
		rewardDTOs.add(new RewardDTO());
		when(userService.getUserId(anyString())).thenReturn(userId);
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(rewards));
		when(rewardConverterDTO.converterRewardsToDTOs(anyList())).thenReturn(rewardDTOs);
		assertEquals(1, rewardService.getUserRewards(userName).size());
	}
	
	@Test
	void getUserRewardsThrowsExceptionWhenUserNotFound() throws HttpException {
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		when(userService.getUserId(anyString())).thenReturn(userId);
		when(rewardRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> rewardService.getUserRewards(userName));
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
	void getAttractionRewardPointsReturnsPointsWhenOk() {
		when(rewardCentral.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(points);
		assertEquals(points, rewardService.getAttractionRewardPoints(UUID.randomUUID(), UUID.randomUUID()));
	}

}
