package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.converters.ConverterDTONearByAttractionImpl;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTOLocation;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTONearByAttraction;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.model.NearByAttraction;

@ExtendWith(MockitoExtension.class)
class ConverterDTONearByAttractionTest {
	
	@Mock
	private IConverterDTOLocation locationConverter;
	
	@InjectMocks
	private IConverterDTONearByAttraction nearByAttractionConverter = new ConverterDTONearByAttractionImpl();
	
	private static MLocation location;
	private static LocationDTO locationDTO;
	private static NearByAttraction nearByAttraction;
	private static List<NearByAttraction> nearByAttractions;
	
	@BeforeAll
	private static void setUp() {
		location = new MLocation(10d, 20d);
		nearByAttraction = new NearByAttraction("name", location, location, 20, 100);
		nearByAttractions = new ArrayList<>();
		nearByAttractions.add(nearByAttraction);
		locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
	}

	@Test
	void convertEntityToDTO() throws ConverterDTOException {
		when(locationConverter.convertEntityToDTO(any(MLocation.class))).thenReturn(locationDTO);
		assertEquals(locationDTO.getLatitude(), nearByAttractionConverter
				.convertEntityToDTO(nearByAttraction).getAttractionLocation().getLatitude());
	}

	@Test
	void convertNearByAttractionsToDTOs() throws ConverterDTOException {
		assertEquals(1, nearByAttractionConverter.convertNearByAttractionsToDTOs(nearByAttractions).size());
	}

}
