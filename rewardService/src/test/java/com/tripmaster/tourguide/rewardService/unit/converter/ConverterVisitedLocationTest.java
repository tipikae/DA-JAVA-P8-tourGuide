package com.tripmaster.tourguide.rewardService.unit.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.rewardService.converterDTO.ILocationConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.VisitedLocationDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

@ExtendWith(MockitoExtension.class)
public class ConverterVisitedLocationTest {

	@Mock
	private ILocationConverterDTO locationConverter;
	
	@InjectMocks
	private VisitedLocationDTOConverterImpl visitedLocationConverter;
		
	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		UUID userId = UUID.randomUUID();
		Location location = new Location();
		location.setLatitude(10d);
		location.setLongitude(20d);
		VisitedLocation visitedLocation = new VisitedLocation();
		visitedLocation.setLocation(location);
		visitedLocation.setUserId(userId);
		visitedLocation.setTimeVisited(new Date());
		when(locationConverter.converterEntityToDTO(any(Location.class))).thenReturn(new LocationDTO());
		assertEquals(userId, visitedLocationConverter.converterEntityToDTO(visitedLocation).getUserId());
	}
	
	@Test
	void convertDTOToEntityReturnsEntityWhenOk() throws ConverterException {
		UUID userId = UUID.randomUUID();
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
		VisitedLocationDTO visitedLocationDTO = new VisitedLocationDTO();
		visitedLocationDTO.setLocation(locationDTO);
		visitedLocationDTO.setTimeVisited(new Date());
		visitedLocationDTO.setUserId(userId);
		when(locationConverter.convertDTOToEntity(any(LocationDTO.class))).thenReturn(new Location());
		assertEquals(userId, visitedLocationConverter.convertDTOToEntity(visitedLocationDTO).getUserId());
	}
	
	@Test
	void convertDTOsToEntitiesReturnsListWhenOk() throws ConverterException {
		UUID userId = UUID.randomUUID();
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
		VisitedLocationDTO visitedLocationDTO = new VisitedLocationDTO();
		visitedLocationDTO.setLocation(locationDTO);
		visitedLocationDTO.setTimeVisited(new Date());
		visitedLocationDTO.setUserId(userId);
		List<VisitedLocationDTO> visitedLocationDTOs = new ArrayList<>();
		visitedLocationDTOs.add(visitedLocationDTO);
		when(locationConverter.convertDTOToEntity(any(LocationDTO.class))).thenReturn(new Location());
		assertEquals(1, visitedLocationConverter.convertDTOsToEntities(visitedLocationDTOs).size());
	}
	
}
