package com.tripmaster.tourguide.rewardService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.rewardService.converterDTO.AttractionDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.converterDTO.IAttractionConverterDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Attraction;

class ConverterAttractionDTOTest {
	
	private IAttractionConverterDTO attractionConverter = new AttractionDTOConverterImpl();

	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		String attractionName = "attractionName";
		Attraction attraction = new Attraction();
		attraction.setAttractionName(attractionName);
		assertEquals(attractionName, attractionConverter.converterEntityToDTO(attraction).getAttractionName());
	}

}
