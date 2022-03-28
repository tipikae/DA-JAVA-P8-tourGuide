/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

/**
 * VisitedLocation-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class VisitedLocationDTOConverterImpl implements IVisitedLocationConverterDTO {

	private static final Logger LOGGER = LoggerFactory.getLogger(VisitedLocationDTOConverterImpl.class);
	
	@Autowired
	private ILocationConverterDTO locationConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocationDTO converterEntityToDTO(VisitedLocation entity) throws ConverterException {
		VisitedLocationDTO visitedLocationDTO = new VisitedLocationDTO();
		
		try {
			visitedLocationDTO.setLocation(locationConverter.converterEntityToDTO(entity.getLocation()));
			visitedLocationDTO.setTimeVisited(entity.getTimeVisited());
			visitedLocationDTO.setUserId(entity.getUserId());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return visitedLocationDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocation convertDTOToEntity(VisitedLocationDTO dto) throws ConverterException {
		VisitedLocation visitedLocation = new VisitedLocation();
		
		try {
			visitedLocation.setLocation(locationConverter.convertDTOToEntity(dto.getLocation()));
			visitedLocation.setTimeVisited(dto.getTimeVisited());
			visitedLocation.setUserId(dto.getUserId());
		} catch (Exception e) {
			LOGGER.debug("convertDTOToEntity: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return visitedLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VisitedLocation> convertDTOsToEntities(List<VisitedLocationDTO> visitedLocationDTOs) 
			throws ConverterException {
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		
		for(VisitedLocationDTO visitedLocationDTO: visitedLocationDTOs) {
			visitedLocations.add(convertDTOToEntity(visitedLocationDTO));
		}
		
		return visitedLocations;
	}

}
