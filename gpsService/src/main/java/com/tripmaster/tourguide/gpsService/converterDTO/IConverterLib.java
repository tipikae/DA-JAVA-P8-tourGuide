/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

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
	 */
	M convertLibModelToModel(L libModel);
}
