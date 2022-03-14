package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;
import com.tripmaster.tourguide.gpsService.service.GpsServiceServiceImpl;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class GpsServiceServiceTest {
	
	@Mock
	private IVisitedLocationRepository visitedLocationRepository;
	
	@Mock
	private GpsUtil gpsUtil;
	
	@InjectMocks
	private GpsServiceServiceImpl gpsService;

	@Test
	void getAttractionsReturnsListWhenOk() {
		Attraction attraction = new Attraction(null, null, null, 0, 0);
		List<Attraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		when(gpsUtil.getAttractions()).thenReturn(attractions);
		assertEquals(1, gpsService.getAttractions().size());
	}

	@Test
	void getUserLocationReturnsVisitedLocationWhenOk() {
		Location location = new Location(10d, 20d);
		VisitedLocation visitedLocation = new VisitedLocation(null, location, null);
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		assertEquals(10d, gpsService.getUserLocation(UUID.randomUUID()).location.latitude);
	}
	
	@Test
	void getAllUsersLastLocationReturnsMapWhenOK() {
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(10d, 20d), new Date());
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		Map<UUID, List<VisitedLocation>> allUsersVisitedLocations = new HashMap<>();
		allUsersVisitedLocations.put(userId, visitedLocations);
		when(visitedLocationRepository.findAll()).thenReturn(allUsersVisitedLocations);
		assertEquals(visitedLocation.location.latitude, 
				gpsService.getAllUsersLastLocation().get(userId).latitude);
	}
	
	@Test
	void getUserVisitedLocationsReturnsListWhenOk() throws UserNotFoundException {
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(10d, 20d), new Date());
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(visitedLocations));
		assertEquals(visitedLocations.get(0).location.longitude, 
				gpsService.getUserVisitedLocations(userId).get(0).location.longitude);
	}
	
	@Test
	void getUserVisitedLocationsThrowsExceptionWhenUserNotFound() throws UserNotFoundException {
		UUID userId = UUID.randomUUID();
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () ->
				gpsService.getUserVisitedLocations(userId));
	}
	
	@Test
	void getUserLastVisitedLocationReturnsVisitedLocationWhenOk() throws UserNotFoundException {
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(10d, 20d), new Date());
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(visitedLocations));
		assertEquals(visitedLocations.get(visitedLocations.size() - 1).location.latitude, 
				gpsService.getUserLastVisitedLocation(userId).location.latitude);
	}
	
	@Test
	void getUserLastVisitedLocationThrowsExceptionWhenUserNotFound() throws UserNotFoundException {
		UUID userId = UUID.randomUUID();
		
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () ->
				gpsService.getUserLastVisitedLocation(userId));
	}
	
	@Test
	void addUserVisitedLocationReturnsVisitedLocationWhenOk() {
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(10d, 20d), new Date());
		when(visitedLocationRepository.save(any(VisitedLocation.class))).thenReturn(visitedLocation);
		assertEquals(visitedLocation.userId, gpsService.addUserVisitedLocation(visitedLocation).userId);
	}

}
