/**
 * 
 */
package com.tripmaster.tourguide.gpsService.util;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.model.NearByAttraction;

/**
 * NearByAttraction operations.
 * @author tipikae
 * @version 1.0
 *
 */
public interface INearByAttractionOperation {

	/**
	 * Get nearby attractions. 
	 * @param userId UUID
	 * @return List
	 * @throws ConverterLibException 
	 * @throws HttpException 
	 */
	List<NearByAttraction> getNearByAttractions(UUID userId) throws ConverterLibException, HttpException;
}
