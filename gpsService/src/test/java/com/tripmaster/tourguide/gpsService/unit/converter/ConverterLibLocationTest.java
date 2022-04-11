package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.gpsService.converters.ConverterLibLocationImpl;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;

import gpsUtil.location.Location;

class ConverterLibLocationTest {
	
	private IConverterLibLocation locationConverter = new ConverterLibLocationImpl();

	@Test
	void convertLibModelToModelReturnsModelWhenOk() throws ConverterLibException {
		Location location = new Location(10d, 20d);
		assertEquals(10d, locationConverter.convertLibModelToModel(location).getLatitude());
	}

}
