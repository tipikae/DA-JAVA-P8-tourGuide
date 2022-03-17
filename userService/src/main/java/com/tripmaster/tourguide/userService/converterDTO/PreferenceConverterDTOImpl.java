/**
 * 
 */
package com.tripmaster.tourguide.userService.converterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.userService.dto.NewPreferenceDTO;
import com.tripmaster.tourguide.userService.exceptions.ConverterException;
import com.tripmaster.tourguide.userService.model.Preference;

/**
 * Preference converter DTO implementation.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class PreferenceConverterDTOImpl implements IPreferenceConverterDTO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PreferenceConverterDTOImpl.class);

	@Override
	public Preference converterDTOToEntity(NewPreferenceDTO dto) throws ConverterException {
		Preference preference = new Preference();
		
		try {
			preference.setAttractionProximity(dto.getAttractionProximity());
			preference.setCurrency(dto.getCurrency());
			preference.setHighPricePoint(dto.getHighPricePoint());
			preference.setLowerPricePoint(dto.getLowerPricePoint());
			preference.setNumberOfAdults(dto.getNumberOfAdults());
			preference.setNumberOfChildren(dto.getNumberOfChildren());
			preference.setTicketQuantity(dto.getTicketQuantity());
			preference.setTripDuration(dto.getTripDuration());
		} catch (Exception e) {
			LOGGER.debug("converterDTOToEntity: error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return preference;
	}

}
