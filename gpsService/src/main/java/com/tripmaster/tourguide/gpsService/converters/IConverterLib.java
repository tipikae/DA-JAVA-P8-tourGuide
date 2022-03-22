/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;

/**
 * Converter library model to application model.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterLib <L, M> {

	/**
	 * Convert library model to application model.
	 * @param libModel L
	 * @return M
	 * @throws ConverterLibException 
	 */
	M convertLibModelToModel(L libModel) throws ConverterLibException;
}
