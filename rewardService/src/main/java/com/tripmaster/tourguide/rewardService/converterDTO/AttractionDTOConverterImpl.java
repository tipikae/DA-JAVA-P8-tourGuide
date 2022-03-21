/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.dto.AttractionDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Attraction;

/**
 * Attraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class AttractionDTOConverterImpl implements IAttractionConverterDTO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttractionDTOConverterImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttractionDTO converterEntityToDTO(Attraction entity) throws ConverterException {
		AttractionDTO attractionDTO = new AttractionDTO();
		
		try {
			attractionDTO.setAttractionId(entity.getAttractionId());
			attractionDTO.setAttractionName(entity.getAttractionName());
			attractionDTO.setCity(entity.getCity());
			attractionDTO.setLatitude(entity.getLatitude());
			attractionDTO.setLongitude(entity.getLongitude());
			attractionDTO.setState(entity.getState());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return attractionDTO;
	}

}
