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

import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.NearByAttraction;

/**
 * NearByAttraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterDTONearByAttractionImpl implements IConverterDTONearByAttraction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterDTONearByAttractionImpl.class);
	
	@Autowired
	private IConverterDTOLocation locationConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NearByAttractionDTO convertEntityToDTO(NearByAttraction entity) throws ConverterDTOException {
		NearByAttractionDTO nearByAttractionDTO = new NearByAttractionDTO();
		
		try {
			nearByAttractionDTO.setAttractionLocation(
					locationConverter.convertEntityToDTO(entity.getAttractionLocation()));
			nearByAttractionDTO.setAttractionName(entity.getAttractionName());
			nearByAttractionDTO.setDistance(entity.getDistance());
			nearByAttractionDTO.setPoints(entity.getPoints());
			nearByAttractionDTO.setUserLocation(
					locationConverter.convertEntityToDTO(entity.getUserLocation()));
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterDTOException(e.getMessage());
		}
		
		return nearByAttractionDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NearByAttractionDTO> convertNearByAttractionsToDTOs(List<NearByAttraction> nearByAttractions) 
			throws ConverterDTOException {
		List<NearByAttractionDTO> nearByAttractionDTOs = new ArrayList<>();
		
		for(NearByAttraction nearByAttraction: nearByAttractions) {
			nearByAttractionDTOs.add(convertEntityToDTO(nearByAttraction));
		}
		
		return nearByAttractionDTOs;
	}

}
