package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.converters.ConverterLibVisitedLocationImpl;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibLocation;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibVisitedLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.model.MLocation;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class ConverterLibVisitedLocationTest {
	
	@Mock
	private IConverterLibLocation converterLocation;
	
	@InjectMocks
	private IConverterLibVisitedLocation converterVisitedLocation = new ConverterLibVisitedLocationImpl();
	
	private static UUID userId;
	private static Location location;
	private static VisitedLocation visitedLocation;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.randomUUID();
		location = new Location(10d, 20d);
		visitedLocation = new VisitedLocation(userId, location, null);
	}

	@Test
	void convertLibModelToModelReturnsModelWhenOk() throws ConverterLibException {
		when(converterLocation.convertLibModelToModel(location)).thenReturn(new MLocation());
		assertEquals(userId, converterVisitedLocation.convertLibModelToModel(visitedLocation).getUserId());
	}
	
	@Test
	void convertLibVisitedLocationsToMVisitedLocationsReturnsListWhenOk() throws ConverterLibException {
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		when(converterLocation.convertLibModelToModel(location)).thenReturn(new MLocation());
		assertEquals(1, converterVisitedLocation.convertLibVisitedLocationsToMVisitedLocations(
				visitedLocations).size());
	}

}
