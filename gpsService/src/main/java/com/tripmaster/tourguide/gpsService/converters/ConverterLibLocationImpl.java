/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.model.MLocation;

import gpsUtil.location.Location;

/**
 * Converter library Location to model Location.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterLibLocationImpl implements IConverterLibLocation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterLibLocationImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MLocation convertLibModelToModel(Location lLocation) throws ConverterLibException {
		MLocation mLocation = new MLocation();
		
		try {
			mLocation.setLatitude(lLocation.latitude);
			mLocation.setLongitude(lLocation.longitude);
		} catch (Exception e) {
			LOGGER.debug("convertLibModelToModel: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterLibException(e.getMessage());
		}
		
		return mLocation;
	}

}
