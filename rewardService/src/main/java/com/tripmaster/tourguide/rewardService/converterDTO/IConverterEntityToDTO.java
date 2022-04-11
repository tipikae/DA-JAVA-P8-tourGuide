/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;

/**
 * Converter DTO-Entity interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterEntityToDTO<E, D> {

	/**
	 * Converter Entity to DTO.
	 * @param entity E
	 * @return D
	 * @throws ConverterException 
	 */
	D converterEntityToDTO(E entity) throws ConverterException;
}
