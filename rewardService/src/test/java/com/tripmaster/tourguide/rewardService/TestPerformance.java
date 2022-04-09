package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;
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
import com.tripmaster.tourguide.rewardService.service.RewardServiceServiceImpl;

@SpringBootTest
class TestPerformance {
	
	@Autowired
	private IRewardServiceService rewardService;
	
	private static final int USER_NUMBER = 100;

	@Test
	void highVolumeGetRewards() {
		// generate 26 random attractions
		List<AttractionDTO> attractions = generateRandomAttractions();
		
		// generate all userIds
		List<UUID> userIds = generateRandomUserIds();
		
		// generate map (see method comment)
		Map<UUID, NewVisitedLocationsAndAttractionsDTO> map = generateUserIdsMap(userIds, attractions);

		// start watch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// call calculateRewards for each userId
		userIds.forEach(id -> {
			try {
				rewardService.calculateRewards(id, map.get(id));
			} catch (HttpException | ConverterException e) {
				fail(e.getMessage());
			}
		});
		
		// waiting for all threads terminated
		RewardServiceServiceImpl.executorService.shutdown();
		try {
			if(!RewardServiceServiceImpl.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
				System.out.println("Shutting down now");
				RewardServiceServiceImpl.executorService.shutdownNow();
			} else {
				System.out.println("All threads shutted down");
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: shutting down");
			RewardServiceServiceImpl.executorService.shutdownNow();
		}
		
		// check rewards updated for all userIds
		userIds.forEach(id -> {
			try {
				assertTrue(rewardService.getUserRewardsPoints(id) > 0);
			} catch (UserNotFoundException e) {
				fail(e.getMessage());
			}
		});
		
		// stop watch
		stopWatch.stop();
		
		System.out.println("highVolumeGetRewards: Time Elapsed: " 
				+ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}
	
	private List<AttractionDTO> generateRandomAttractions() {
		return IntStream.range(0, 26)
				.mapToObj(i -> new AttractionDTO(UUID.randomUUID(), "name" + i, "city" + i, "state" + i, 
						generateRandomLatitude(), generateRandomLongitude()))
				.collect(Collectors.toList());
	}
	
	private List<UUID> generateRandomUserIds() {
		return IntStream.range(0, USER_NUMBER)
				.mapToObj(i -> UUID.randomUUID())
				.collect(Collectors.toList());
	}
	
	// Each userId key is associated with a NewVisitedLocationsAndAttractionsDTO object.
	// A NewVisitedLocationsAndAttractionsDTO object contains an attractions list and a visitedLocations list.
	// The visitedLocations list has 3 random VisitedLocationDTO objects and 1 at an attraction location:
	// 		-> only 1 attraction is near a visitedLocation.
	private Map<UUID, NewVisitedLocationsAndAttractionsDTO> generateUserIdsMap(List<UUID> userIds, 
			List<AttractionDTO> attractions) {
		Map<UUID, NewVisitedLocationsAndAttractionsDTO> map = new HashMap<>();
		userIds.forEach(id -> {
			List<VisitedLocationDTO> visitedLocations = generateUserLocationHistory(id);
			visitedLocations.add(new VisitedLocationDTO(id, attractions.get(0), getRandomTime()));
			
			NewVisitedLocationsAndAttractionsDTO visitedLocationsAndAttractions = 
					new NewVisitedLocationsAndAttractionsDTO();
			visitedLocationsAndAttractions.setAttractions(attractions);
			visitedLocationsAndAttractions.setVisitedLocations(visitedLocations);
			
			map.put(id, visitedLocationsAndAttractions);
		});
		System.out.println("Created " + USER_NUMBER + " internal test users.");
		
		return map;
	}
	
	private List<VisitedLocationDTO> generateUserLocationHistory(UUID userId) {
		List<VisitedLocationDTO> visitedLocations = new ArrayList<>();
		IntStream.range(0, 3).forEach(i-> {
			visitedLocations.add(new VisitedLocationDTO(
					userId, 
					new LocationDTO(generateRandomLatitude(), generateRandomLongitude()), 
					getRandomTime()));
		});
		
		return visitedLocations;
	}
	
	private double generateRandomLongitude() {
		double leftLimit = -180;
	    double rightLimit = 180;
	    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}
	
	private double generateRandomLatitude() {
		double leftLimit = -85.05112878;
	    double rightLimit = 85.05112878;
	    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
	}
	
	private Date getRandomTime() {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
	    return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
	}
}
