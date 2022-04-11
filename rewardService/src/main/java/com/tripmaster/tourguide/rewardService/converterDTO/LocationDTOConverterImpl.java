/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.dto.LocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.Location;

/**
 * Location-DTO converter implementation.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class LocationDTOConverterImpl implements ILocationConverterDTO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationDTOConverterImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocationDTO converterEntityToDTO(Location entity) throws ConverterException {
		LocationDTO locationDTO = new LocationDTO();
		
		try {
			locationDTO.setLatitude(entity.getLatitude());
			locationDTO.setLongitude(entity.getLongitude());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return locationDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Location convertDTOToEntity(LocationDTO dto) throws ConverterException {
		Location location = new Location();
		
		try {
			location.setLatitude(dto.getLatitude());
			location.setLongitude(dto.getLongitude());
		} catch (Exception e) {
			LOGGER.debug("convertDTOToEntity: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return location;
	}

}
