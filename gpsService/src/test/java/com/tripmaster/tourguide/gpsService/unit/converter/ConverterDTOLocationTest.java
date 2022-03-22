package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.gpsService.converterDTO.ConverterDTOLocationImpl;
import com.tripmaster.tourguide.gpsService.converterDTO.IConverterDTOLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.MLocation;

class ConverterDTOLocationTest {
	
	private IConverterDTOLocation locationConverter = new ConverterDTOLocationImpl();

	@Test
	void convertEntityToDTO() throws ConverterException {
		MLocation location = new MLocation(10d, 20d);
		assertEquals(10d, locationConverter.convertEntityToDTO(location).getLatitude());
	}

}
