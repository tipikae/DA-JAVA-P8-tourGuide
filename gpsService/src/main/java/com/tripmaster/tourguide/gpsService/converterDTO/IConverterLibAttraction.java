/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import java.util.List;

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
	 * @param attractions List<Attraction>
	 * @return List<MAttraction>
	 */
	List<MAttraction> convertLibAttractionsToMAttractions(List<Attraction> attractions);
}
