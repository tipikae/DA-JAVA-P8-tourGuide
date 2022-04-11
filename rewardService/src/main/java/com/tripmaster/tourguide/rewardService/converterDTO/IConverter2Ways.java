/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;

/**
 * Converter DTO and entity in two ways.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverter2Ways<E, D> extends IConverterEntityToDTO<E, D> {

	/**
	 * Convert DTO to entity.
	 * @param dto D
	 * @return E
	 * @throws ConverterException 
	 */
	E convertDTOToEntity(D dto) throws ConverterException;
}
