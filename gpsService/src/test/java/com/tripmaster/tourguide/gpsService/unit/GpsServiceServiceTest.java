package com.tripmaster.tourguide.gpsService.unit;

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

import org.junit.jupiter.api.BeforeAll;
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
	
	private static String userName;
	private static UUID userId;
	private static Location location;
	private static Date timeVisited;
	private static VisitedLocation visitedLocation;
	private static List<VisitedLocation> visitedLocations;
	private static Attraction attraction;
	private static List<Attraction> attractions;
	
	@BeforeAll
	private static void setUp() {
		userName = "userName";
		userId = UUID.randomUUID();
		location = new Location(10d, 20d);
		timeVisited = new Date();
		visitedLocation = new VisitedLocation(userId, location, timeVisited);
		visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		attraction = new Attraction("name", "city", "state", 10d, 20d);
		attractions = new ArrayList<>();
		attractions.add(attraction);
	}

	@Test
	void getAttractionsReturnsListWhenOk() {
		when(gpsUtil.getAttractions()).thenReturn(attractions);
		assertEquals(1, gpsService.getAttractions().size());
	}

	/*@Test
	void getUserLocationReturnsVisitedLocationWhenOk() {
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		assertEquals(10d, gpsService.getUserLocation(userName).location.latitude);
	}

	@Test
	void getUserLocationThrowsExceptionWhenUserNotFound() {
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		assertEquals(10d, gpsService.getUserLocation(userName).location.latitude);
	}*/
	
	@Test
	void getAllUsersLastLocationReturnsMapWhenOK() {
		Map<UUID, List<VisitedLocation>> allUsersVisitedLocations = new HashMap<>();
		allUsersVisitedLocations.put(userId, visitedLocations);
		when(visitedLocationRepository.findAll()).thenReturn(allUsersVisitedLocations);
		assertEquals(visitedLocation.location.latitude, 
				gpsService.getAllUsersLastLocation().get(userId).latitude);
	}
	
	@Test
	void getUserVisitedLocationsReturnsListWhenOk() throws UserNotFoundException {
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.of(visitedLocations));
		assertEquals(visitedLocations.get(0).location.longitude, 
				gpsService.getUserVisitedLocations(userId).get(0).location.longitude);
	}
	
	@Test
	void getUserVisitedLocationsThrowsExceptionWhenUserNotFound() throws UserNotFoundException {
		when(visitedLocationRepository.findByUserId(any(UUID.class))).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () ->
				gpsService.getUserVisitedLocations(userId));
	}
	
	@Test
	void getNearbyAttractionsReturnsListWhenOk() throws UserNotFoundException {
		when(gpsUtil.getAttractions()).thenReturn(attractions);
		assertFalse(gpsService.getNearByAttractions(userName).isEmpty());
	}
	
	@Test
	void getNearbyAttractionsThrowsExceptionWhenUserNotFound() {
		
	}

}
