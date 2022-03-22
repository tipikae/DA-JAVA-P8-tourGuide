/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;

import gpsUtil.location.Attraction;

/**
 * Converter library Attraction to model Attraction.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterLibAttractionImpl implements IConverterLibAttraction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterLibAttractionImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MAttraction convertLibModelToModel(Attraction lAttraction) throws ConverterLibException {
		MAttraction mAttraction = new MAttraction();
		
		try {
			mAttraction.setAttractionId(lAttraction.attractionId);
			mAttraction.setAttractionName(lAttraction.attractionName);
			mAttraction.setCity(lAttraction.city);
			mAttraction.setLatitude(lAttraction.latitude);
			mAttraction.setLongitude(lAttraction.longitude);
			mAttraction.setState(lAttraction.state);
		} catch (Exception e) {
			LOGGER.debug("convertLibModelToModel: exception: " + e.getClass().getSimpleName() 
					+ ", error: " + e.getMessage());
			throw new ConverterLibException(e.getMessage());
		}
		
		return mAttraction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MAttraction> convertLibAttractionsToMAttractions(List<Attraction> attractions) 
			throws ConverterLibException {
		List<MAttraction> mAttractions = new ArrayList<>();
		
		for(Attraction attraction: attractions) {
			mAttractions.add(convertLibModelToModel(attraction));
		}
		
		return mAttractions;
	}

}
