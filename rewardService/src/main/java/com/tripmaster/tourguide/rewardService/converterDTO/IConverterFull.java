/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;

/**
 * Converter DTO and entity
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterFull<E, D> extends IConverter2Ways<E, D> {

	/**
	 * Convert DTOs list to entities list.
	 * @param dtos List
	 * @return List
	 * @throws ConverterException 
	 */
	List<E> convertDTOsToEntities(List<D> dtos) throws ConverterException;
}
