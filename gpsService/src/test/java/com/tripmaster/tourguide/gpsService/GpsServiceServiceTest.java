package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;
import com.tripmaster.tourguide.gpsService.service.GpsServiceServiceImpl;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class GpsServiceServiceTest {
	
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
	void getUserLocationReturnsVisitedLocationWhenOk() throws CustomNumberFormatException {
		Location location = new Location(10d, 20d);
		VisitedLocation visitedLocation = new VisitedLocation(null, location, null);
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		assertEquals(10d, gpsService.getUserLocation(UUID.randomUUID()).location.latitude);
	}

	@Test
	void getUserLocationThrowsCustomNumberFormatExceptionWhenNumberFormatException() {
		doThrow(NumberFormatException.class).when(gpsUtil).getUserLocation(any(UUID.class));
		assertThrows(CustomNumberFormatException.class, () -> gpsService.getUserLocation(UUID.randomUUID()));
	}

}
