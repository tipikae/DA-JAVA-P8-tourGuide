/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;

/**
 * Converter DTO generic interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTO <E, D> {

	/**
	 * Converter entity to DTO.
	 * @param entity E
	 * @return D
	 * @throws ConverterException 
	 */
	D convertEntityToDTO(E entity) throws ConverterException;
}
