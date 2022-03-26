/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;

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
	 * @throws ConverterDTOException 
	 */
	D convertEntityToDTO(E entity) throws ConverterDTOException;
}
