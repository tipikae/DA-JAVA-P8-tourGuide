package com.tripmaster.tourguide.userService.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.userService.converterDTO.IPreferenceConverterDTO;
import com.tripmaster.tourguide.userService.converterDTO.PreferenceConverterDTOImpl;
import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;

@ExtendWith(MockitoExtension.class)
class PreferenceConverterDTOTest {
	
	private IPreferenceConverterDTO preferenceConverterDTO = new PreferenceConverterDTOImpl();

	@Test
	void DTOconverterToEntityReturnsEntityWhenOk() throws ConverterException {
		NewPreferenceDTO newPreferenceDTO = new NewPreferenceDTO();
		newPreferenceDTO.setNumberOfAdults(2);
		assertEquals(2, preferenceConverterDTO.converterDTOToEntity(newPreferenceDTO).getNumberOfAdults());
	}

}
