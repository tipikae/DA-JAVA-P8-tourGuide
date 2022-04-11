package com.tripmaster.tourguide.rewardService.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tripmaster.tourguide.rewardService.converterDTO.ILocationConverterDTO;
import com.tripmaster.tourguide.rewardService.converterDTO.LocationDTOConverterImpl;
import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Location;

class ConverterLocationDTOTest {
	
	private ILocationConverterDTO locationConverter = new LocationDTOConverterImpl();

	@Test
	void converterEntityToDTOReturnsDTOWhenOk() throws ConverterException {
		Location location = new Location();
		location.setLatitude(10d);
		location.setLongitude(20d);
		assertEquals(20d, locationConverter.converterEntityToDTO(location).getLongitude());
	}
	
	@Test
	void convertDTOToEntityReturnsEntityWhenOk() throws ConverterException {
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
		assertEquals(10d, locationConverter.convertDTOToEntity(locationDTO).getLatitude());
	}

}
