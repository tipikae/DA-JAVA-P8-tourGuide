package com.tripmaster.tourguide.rewardService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.rewardService.converterDTO.AttractionDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.converterDTO.IAttractionConverterDTO;
import com.tripmaster.tourguide.rewardService.dto.AttractionDTO;
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
	
	@Test
	void convertDTOToEntityReturnsEntityWhenOk() throws ConverterException {
		String attractionName = "attractionName";
		AttractionDTO attractionDTO = new AttractionDTO();
		attractionDTO.setAttractionName(attractionName);
		assertEquals(attractionName, attractionConverter.convertDTOToEntity(attractionDTO).getAttractionName());
	}

	@Test
	void convertDTOsToEntitiesReturnsListWhenOk() throws ConverterException {
		AttractionDTO attractionDTO1 = new AttractionDTO();
		AttractionDTO attractionDTO2 = new AttractionDTO();
		List<AttractionDTO> attractionDTOs = new ArrayList<>();
		attractionDTOs.add(attractionDTO1);
		attractionDTOs.add(attractionDTO2);
		assertEquals(2, attractionConverter.convertDTOsToEntities(attractionDTOs).size());
	}
}
