/**
 * 
 */
package com.tripmaster.tourguide.gpsService.util;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
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
	 * @return List<NearByAttraction>
	 * @throws ConverterLibException 
	 */
	List<NearByAttraction> getNearByAttractions(UUID userId) throws ConverterLibException;
}
