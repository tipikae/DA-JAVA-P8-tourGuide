/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Attraction convertDTOToEntity(AttractionDTO dto) throws ConverterException {
		Attraction attraction = new Attraction();
		
		try {
			attraction.setAttractionId(dto.getAttractionId());
			attraction.setAttractionName(dto.getAttractionName());
			attraction.setCity(dto.getCity());
			attraction.setLatitude(dto.getLatitude());
			attraction.setLongitude(dto.getLongitude());
			attraction.setState(dto.getState());
		} catch (Exception e) {
			LOGGER.debug("convertDTOToEntity: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return attraction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Attraction> convertDTOsToEntities(List<AttractionDTO> dtos) throws ConverterException {
		List<Attraction> attractions = new ArrayList<>();
		
		for(AttractionDTO attractionDTO: dtos) {
			attractions.add(convertDTOToEntity(attractionDTO));
		}
		
		return attractions;
	}

}
