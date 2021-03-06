package com.tripmaster.tourguide.gpsService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.gpsService.converters.ConverterDTOAttractionImpl;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTOAttraction;
import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;

class ConverterDTOAttractionTest {
	
	private IConverterDTOAttraction attractionConverter = new ConverterDTOAttractionImpl();
	
	private static String attractionName = "attractionName";
	private static MAttraction attraction;
	private static AttractionDTO attractionDTO;
	
	@BeforeAll
	private static void setUp() {
		attraction = new MAttraction(attractionName, null, null, 0, 0);
		attractionDTO = new AttractionDTO();
		attractionDTO.setAttractionName(attractionName);
	}

	@Test
	void convertEntityToDTO() throws ConverterDTOException {
		assertEquals(attractionName, attractionConverter.convertEntityToDTO(attraction).getAttractionName());
	}

	@Test
	void convertAttractionsToDTos() throws ConverterDTOException {
		List<MAttraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		assertEquals(1, attractionConverter.convertAttractionsToDTos(attractions).size());
	}

}
