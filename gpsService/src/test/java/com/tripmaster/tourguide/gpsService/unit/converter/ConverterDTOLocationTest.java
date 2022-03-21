package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.gpsService.converterDTO.ConverterDTOLocationImpl;
import com.tripmaster.tourguide.gpsService.converterDTO.IConverterDTOLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.Location;

class ConverterDTOLocationTest {
	
	private IConverterDTOLocation locationConverter = new ConverterDTOLocationImpl();

	@Test
	void convertEntityToDTO() throws ConverterException {
		Location location = new Location(10d, 20d);
		assertEquals(10d, locationConverter.convertEntityToDTO(location).getLatitude());
	}

}
