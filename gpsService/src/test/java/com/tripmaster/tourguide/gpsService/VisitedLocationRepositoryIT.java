package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;

@SpringBootTest
class VisitedLocationRepositoryIT {
	
	@Autowired
	private IVisitedLocationRepository visitedLocationRepository;

	@Test
	void test() {
		UUID userId = UUID.randomUUID();
		MLocation location = new MLocation(10d, 20d);
		Date timeVisited = new Date();
		MVisitedLocation visitedLocation = new MVisitedLocation(userId, location, timeVisited);
		
		// save
		assertEquals(visitedLocation, visitedLocationRepository.save(visitedLocation));
		
		// findByUserId
		assertTrue(visitedLocationRepository.findByUserId(userId).isPresent());
		assertEquals(1, visitedLocationRepository.findByUserId(userId).get().size());
		
		// findAll
		Map<UUID, List<MVisitedLocation>> allVisitedLocations = visitedLocationRepository.findAll();
		assertEquals(location, allVisitedLocations.get(userId).get(0).getLocation());
	}

}
