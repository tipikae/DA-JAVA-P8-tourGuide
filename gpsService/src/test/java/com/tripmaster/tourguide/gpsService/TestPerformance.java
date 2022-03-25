package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;
import com.tripmaster.tourguide.gpsService.tracker.Tracker;

@SpringBootTest
class TestPerformance {
	
	@Autowired
	private IGpsServiceService gpsService;
	
	@Autowired
	private Tracker tracker;
	
	private int userNumber = 1000;

	@Test
	public void highVolumeTrackLocation() 
			throws ConverterLibException, ConverterDTOException, HttpException {
		tracker.stopTracking();
		
		List<UUID> userIds = new ArrayList<>();
		for(int i = 0; i < userNumber; i++) {
			UUID userId = UUID.randomUUID();
			userIds.add(userId);
		}
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		userIds.parallelStream().forEach(userId -> {
			try {
				gpsService.trackUserLocation(userId);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		});
		stopWatch.stop();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " 
				+ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

}
