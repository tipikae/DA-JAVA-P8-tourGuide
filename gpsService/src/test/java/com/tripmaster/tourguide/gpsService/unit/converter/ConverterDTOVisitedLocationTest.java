package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.tripmaster.tourguide.gpsService.converterDTO.ConverterDTOVisitedLocationImpl;
import com.tripmaster.tourguide.gpsService.converterDTO.IConverterDTOLocation;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.Location;
import com.tripmaster.tourguide.gpsService.model.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class ConverterDTOVisitedLocationTest {
	
	@Mock
	private IConverterDTOLocation locationConverter;
	
	@InjectMocks
	private ConverterDTOVisitedLocationImpl visitedLocationConverter = new ConverterDTOVisitedLocationImpl();
	
	private static UUID userId;
	private static VisitedLocation visitedLocation;
	private static Location location;
	private static LocationDTO locationDTO;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.randomUUID();
		location = new Location(10d, 20d);
		visitedLocation = new VisitedLocation(userId, location, null);
		locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
	}

	@Test
	void convertEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		when(locationConverter.convertEntityToDTO(any(Location.class))).thenReturn(locationDTO);
		assertEquals(userId, visitedLocationConverter.convertEntityToDTO(visitedLocation).getUserId());
	}

	@Test
	void convertVisitedLocationsToDTOsReturnsListWhenOk() throws ConverterException {
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		when(locationConverter.convertEntityToDTO(any(Location.class))).thenReturn(locationDTO);
		assertEquals(1, visitedLocationConverter.convertVisitedLocationsToDTOs(visitedLocations).size());
	}

}
