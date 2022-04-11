package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.gpsService.converters.ConverterLibAttractionImpl;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibAttraction;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;

import gpsUtil.location.Attraction;

class ConverterLibAttractionTest {
	
	private IConverterLibAttraction attractionConverter = new ConverterLibAttractionImpl();
	
	private static Attraction attraction;
	
	@BeforeAll
	private static void setUp() {
		attraction = new Attraction("attractionName", "city", "state", 10d, 20d);
	}

	@Test
	void convertLibModelToModelReturnsModelWhenOk() throws ConverterLibException {
		assertEquals(10d, attractionConverter.convertLibModelToModel(attraction).getLatitude());
	}

	@Test
	void convertLibAttractionsToMAttractionsReturnsListWhenOk() throws ConverterLibException {
		List<Attraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		assertEquals(1, attractionConverter.convertLibAttractionsToMAttractions(attractions).size());
	}

}
