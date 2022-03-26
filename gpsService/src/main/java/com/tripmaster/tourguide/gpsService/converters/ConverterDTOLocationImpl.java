/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MLocation;

/** Location-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterDTOLocationImpl implements IConverterDTOLocation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterDTOLocationImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocationDTO convertEntityToDTO(MLocation entity) throws ConverterDTOException {
		LocationDTO locationDTO = new LocationDTO();
		
		try {
			locationDTO.setLatitude(entity.getLatitude());
			locationDTO.setLongitude(entity.getLongitude());
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterDTOException(e.getMessage());
		}
		
		return locationDTO;
	}

}
