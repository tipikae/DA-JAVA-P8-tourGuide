/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;

/**
 * Attraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterDTOAttractionImpl implements IConverterDTOAttraction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterDTOAttractionImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttractionDTO convertEntityToDTO(MAttraction entity) throws ConverterException {
		AttractionDTO attractionDTO = new AttractionDTO();
		
		try {
			attractionDTO.setAttractionId(entity.attractionId);
			attractionDTO.setAttractionName(entity.attractionName);
			attractionDTO.setCity(entity.city);
			attractionDTO.setLatitude(entity.latitude);
			attractionDTO.setLongitude(entity.longitude);
			attractionDTO.setState(entity.state);
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return attractionDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AttractionDTO> convertAttractionsToDTos(List<MAttraction> attractions) 
			throws ConverterException {
		List<AttractionDTO> attractionDTOs = new ArrayList<>();
		
		for(MAttraction attraction: attractions) {
			attractionDTOs.add(convertEntityToDTO(attraction));
		}
		
		return attractionDTOs;
	}

}
