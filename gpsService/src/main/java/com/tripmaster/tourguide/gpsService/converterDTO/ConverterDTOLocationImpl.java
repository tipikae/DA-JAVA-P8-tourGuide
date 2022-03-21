/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.Location;

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
	public LocationDTO convertEntityToDTO(Location entity) throws ConverterException {
		LocationDTO locationDTO = new LocationDTO();
		
		try {
			locationDTO.setLatitude(entity.latitude);
			locationDTO.setLongitude(entity.longitude);
		} catch (Exception e) {
			LOGGER.debug("converterEntityToDTO: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterException(e.getMessage());
		}
		
		return locationDTO;
	}

}
