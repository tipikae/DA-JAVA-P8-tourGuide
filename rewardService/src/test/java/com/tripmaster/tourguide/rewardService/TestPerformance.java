package com.tripmaster.tourguide.rewardService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private RewardServiceServiceImpl rewardService;
	
	private int userNumber = 100;

	@Test
	void highVolumeGetRewards() {
		List<AttractionDTO> attractions = IntStream.range(0, 26)
				.mapToObj(i -> new AttractionDTO(UUID.randomUUID(), "name" + i, "city" + i, "state" + i))
				.collect(Collectors.toList());	
		
		List<UUID> userIds = IntStream.range(0, userNumber)
				.mapToObj(i -> UUID.randomUUID())
				.collect(Collectors.toList());
		
		LocationDTO location = new LocationDTO(1d, 1d);
		
		Map<UUID, NewVisitedLocationsAndAttractionsDTO> map = new HashMap<>();
		userIds.forEach(id -> {
			NewVisitedLocationsAndAttractionsDTO visitedLocationsAndAttractions = 
					new NewVisitedLocationsAndAttractionsDTO();
			visitedLocationsAndAttractions.setAttractions(attractions);
			visitedLocationsAndAttractions.setVisitedLocations(Arrays.asList(
					new VisitedLocationDTO(id, location, new Date())));
			map.put(id, visitedLocationsAndAttractions);
		});

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		userIds.forEach(id -> {
			try {
				rewardService.calculateRewards(id, map.get(id));
			} catch (HttpException | ConverterException e) {
				fail(e.getMessage());
			}
		});
		
		rewardService.futures.forEach(future -> future.join());
		
		userIds.forEach(id -> {
			try {
				assertTrue(rewardService.getUserRewardsPoints(id) > 0);
			} catch (UserNotFoundException e) {
				fail(e.getMessage());
			}
		});
		
		stopWatch.stop();
		
		System.out.println("highVolumeGetRewards: Time Elapsed: " 
				+ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

}
