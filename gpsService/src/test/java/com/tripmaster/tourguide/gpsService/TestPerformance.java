package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;
import com.tripmaster.tourguide.gpsService.service.GpsServiceServiceImpl;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;
import com.tripmaster.tourguide.gpsService.tracker.Tracker;

@SpringBootTest
class TestPerformance {
	
	@Autowired
	private IGpsServiceService gpsService;
	
	@Autowired
	private IVisitedLocationRepository visitedLocationRepository;
	
	@Autowired
	private Tracker tracker;
	
	private static final int USER_NUMBER = 100;

	@Test
	public void highVolumeTrackLocation() 
			throws ConverterLibException, ConverterDTOException, HttpException {
		tracker.stopTracking();
		
		// generate all userIds
		List<UUID> userIds = generateRandomUserIds();
		System.out.println("Created " + USER_NUMBER + " internal test users.");
		
		// generate visitedLocations
		generateVisitedLocations(userIds);
		
		// start watch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// call trackUserLocation for each userId
		userIds.forEach(userId -> {
			try {
				gpsService.trackUserLocation(userId);
			} catch (ConverterLibException | HttpException e) {
				fail(e.getMessage());
			}
		});
		
		// waiting for all threads terminated
		GpsServiceServiceImpl.executorService.shutdown();
		try {
			if(!GpsServiceServiceImpl.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)) {
				System.out.println("Shutting down now");
				GpsServiceServiceImpl.executorService.shutdownNow();
			} else {
				System.out.println("All threads shutted down");
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: shutting down");
			GpsServiceServiceImpl.executorService.shutdownNow();
		}
		
		// stop watch
		stopWatch.stop();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " 
				+ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

	private List<UUID> generateRandomUserIds() {
		return IntStream.range(0, USER_NUMBER)
				.mapToObj(i -> UUID.randomUUID())
				.collect(Collectors.toList());
	}
	
	private void generateVisitedLocations(List<UUID> userIds) {
		userIds.forEach(id -> {
			List<MVisitedLocation> visitedLocations = generateUserLocationHistory(id);
			visitedLocations.forEach(visitedLocation -> {
				visitedLocationRepository.save(visitedLocation);
			});
		});
	}
	
	private List<MVisitedLocation> generateUserLocationHistory(UUID userId) {
		List<MVisitedLocation> visitedLocations = new ArrayList<>();
		IntStream.range(0, 3).forEach(i-> {
			visitedLocations.add(new MVisitedLocation(
					userId, 
					new MLocation(generateRandomLatitude(), generateRandomLongitude()), 
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
