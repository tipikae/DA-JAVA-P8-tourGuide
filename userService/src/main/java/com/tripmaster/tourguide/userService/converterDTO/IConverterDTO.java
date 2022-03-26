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
public interface IConverterDTO <E, D, N> {

	/**
	 * Convert DTO to entity
	 * @param newDto N
	 * @return E
	 * @throws ConverterException
	 */
	E converterDTOToEntity(N newDto) throws ConverterException;
	
	/**
	 * Converter Entity to DTO.
	 * @param entity E
	 * @return D
	 * @throws ConverterException
	 */
	D converterEntityToDTO(E entity) throws ConverterException;
}
