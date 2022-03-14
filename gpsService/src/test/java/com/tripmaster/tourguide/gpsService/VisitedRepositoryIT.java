package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.exceptions.CustomClassCastException;
import com.tripmaster.tourguide.gpsService.exceptions.CustomIllegalArgumentException;
import com.tripmaster.tourguide.gpsService.exceptions.FindByUserIdOperationException;
import com.tripmaster.tourguide.gpsService.exceptions.SaveOperationException;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest
class VisitedRepositoryIT {
	
	@Autowired
	private IVisitedLocationRepository visitedLocationRepository;

	@Test
	void test() 
			throws CustomClassCastException, CustomIllegalArgumentException, SaveOperationException, 
			FindByUserIdOperationException {
		UUID userId = UUID.randomUUID();
		Location location = new Location(10d, 20d);
		Date timeVisited = new Date();
		VisitedLocation visitedLocation = new VisitedLocation(userId, location, timeVisited);
		
		// save
		assertEquals(visitedLocation, visitedLocationRepository.save(visitedLocation));
		
		// findByUserId
		assertTrue(visitedLocationRepository.findByUserId(userId).isPresent());
		assertEquals(1, visitedLocationRepository.findByUserId(userId).get().size());
		
		// findAll
		Map<UUID, List<VisitedLocation>> allVisitedLocations = visitedLocationRepository.findAll();
		assertEquals(location, allVisitedLocations.get(userId).get(0).location);
	}

}
