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

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

import gpsUtil.location.VisitedLocation;

/**
 * Converter library VisitedLocation to model VisitedLocation.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterLibVisitedLocationImpl implements IConverterLibVisitedLocation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterLibVisitedLocationImpl.class);
	
	@Autowired
	private IConverterLibLocation converterLocation;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MVisitedLocation convertLibModelToModel(VisitedLocation lVisitedLocation) 
			throws ConverterLibException {
		MVisitedLocation mVisitedLocation = new MVisitedLocation();
		
		try {
			mVisitedLocation.setLocation(converterLocation.convertLibModelToModel(lVisitedLocation.location));
			mVisitedLocation.setTimeVisited(lVisitedLocation.timeVisited);
			mVisitedLocation.setUserId(lVisitedLocation.userId);
		} catch (Exception e) {
			LOGGER.debug("convertLibModelToModel: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterLibException(e.getMessage());
		}
		
		return mVisitedLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MVisitedLocation> convertLibVisitedLocationsToMVisitedLocations(
			List<VisitedLocation> lVisitedLocations) throws ConverterLibException {
		List<MVisitedLocation> mVisitedLocations = new ArrayList<>();
		
		for(VisitedLocation visitedLocation: lVisitedLocations) {
			mVisitedLocations.add(convertLibModelToModel(visitedLocation));
		}
		
		return mVisitedLocations;
	}

}
