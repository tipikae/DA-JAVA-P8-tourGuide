/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

/**
 * VisitedLocation-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterDTOVisitedLocationImpl implements IConverterDTOVisitedLocation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterDTOVisitedLocationImpl.class);
	
	@Autowired
	private IConverterDTOLocation locationDTOConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocationDTO convertEntityToDTO(MVisitedLocation entity) throws ConverterDTOException {
		VisitedLocationDTO visitedLocationDTO = new VisitedLocationDTO();
		
		try {
			visitedLocationDTO.setLocation(locationDTOConverter.convertEntityToDTO(entity.getLocation()));
			visitedLocationDTO.setTimeVisited(entity.getTimeVisited());
			visitedLocationDTO.setUserId(entity.getUserId());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterDTOException(e.getMessage());
		}
		
		return visitedLocationDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VisitedLocationDTO> convertVisitedLocationsToDTOs(List<MVisitedLocation> visitedLocations) 
			throws ConverterDTOException {
		List<VisitedLocationDTO> visitedLocationDTOs = new ArrayList<>();
		
		for(MVisitedLocation visitedLocation: visitedLocations) {
			visitedLocationDTOs.add(convertEntityToDTO(visitedLocation));
		}
		
		return visitedLocationDTOs;
	}

}
