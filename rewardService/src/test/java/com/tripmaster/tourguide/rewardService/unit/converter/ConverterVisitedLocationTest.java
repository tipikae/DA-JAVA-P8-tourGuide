package com.tripmaster.tourguide.rewardService.unit.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.converterDTO.ILocationConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.VisitedLocationDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

@ExtendWith(MockitoExtension.class)
public class ConverterVisitedLocationTest {

	@Mock
	private ILocationConverterDTO locationConverter;
	
	@InjectMocks
	private VisitedLocationDTOConverterImpl visitedLocationConverter;
	
	private static Location location;
	private static VisitedLocation visitedLocation;
	private static UUID userId;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.randomUUID();
		location = new Location();
		location.setLatitude(10d);
		location.setLongitude(20d);
		visitedLocation = new VisitedLocation();
		visitedLocation.setLocation(location);
		visitedLocation.setUserId(userId);
		visitedLocation.setTimeVisited(new Date());
	}
	
	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		when(locationConverter.converterEntityToDTO(any(Location.class))).thenReturn(new LocationDTO());
		assertEquals(userId, visitedLocationConverter.converterEntityToDTO(visitedLocation).getUserId());
	}
}
