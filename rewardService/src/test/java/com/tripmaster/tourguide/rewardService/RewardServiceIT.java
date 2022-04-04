package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.rewardService.dto.AttractionDTO;
import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.dto.NewVisitedLocationsAndAttractionsDTO;
import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
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
	void calculateRewards() throws HttpException, ConverterException, UserNotFoundException {
		AttractionDTO attractionDTO = new AttractionDTO();
		attractionDTO.setAttractionId(UUID.randomUUID());
		attractionDTO.setAttractionName("testName");
		attractionDTO.setCity("testCity");
		attractionDTO.setLatitude(10d);
		attractionDTO.setLongitude(20d);
		attractionDTO.setState("testState");
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
		VisitedLocationDTO visitedLocationDTO = new VisitedLocationDTO();
		visitedLocationDTO.setLocation(locationDTO);
		visitedLocationDTO.setTimeVisited(new Date());
		visitedLocationDTO.setUserId(userId);
		List<AttractionDTO> attractionDTOs = Arrays.asList(attractionDTO);
		List<VisitedLocationDTO> visitedLocationDTOs = Arrays.asList(visitedLocationDTO);
		NewVisitedLocationsAndAttractionsDTO newVisitedLocationsAndAttractionsDTO = 
				new NewVisitedLocationsAndAttractionsDTO();
		newVisitedLocationsAndAttractionsDTO.setAttractions(attractionDTOs);
		newVisitedLocationsAndAttractionsDTO.setVisitedLocations(visitedLocationDTOs);
		rewardService.calculateRewards(userId, newVisitedLocationsAndAttractionsDTO);
		assertTrue(rewardService.getUserRewards(userName).size() > 0);
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
