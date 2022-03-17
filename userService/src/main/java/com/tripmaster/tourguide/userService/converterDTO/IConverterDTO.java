/**
 * 
 */
package com.tripmaster.tourguide.userService.converterDTO;

import com.tripmaster.tourguide.userService.exceptions.ConverterException;

/**
 * Generic DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTO <E, D> {

	/**
	 * Convert DTO to entity
	 * @param dto D
	 * @return E
	 * @throws ConverterException
	 */
	E converterDTOToEntity(D dto) throws ConverterException;
}
