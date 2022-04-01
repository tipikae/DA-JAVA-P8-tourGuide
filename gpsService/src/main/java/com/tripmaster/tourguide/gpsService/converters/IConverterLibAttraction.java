/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.List;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;

import gpsUtil.location.Attraction;

/**
 * Converter library Attraction to model Attraction.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterLibAttraction extends IConverterLib<Attraction, MAttraction> {

	/**
	 * Convert library attractions list to model attractions list.
	 * @param attractions List
	 * @return List
	 * @throws ConverterLibException 
	 */
	List<MAttraction> convertLibAttractionsToMAttractions(List<Attraction> attractions) 
			throws ConverterLibException;
}
