/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.List;

/**
 * Full DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IFullConverter<E, D> extends IConverter<E, D> {

	/**
	 * Convert a D DTO to an E entity.
	 * @param dto D
	 * @return E
	 */
	E convertDTOToEntity(D dto);
	
	/**
	 * Convert a D list to an E list.
	 * @param dtos List<D>
	 * @return List<E>
	 */
	List<E> convertDTOsToEntities(List<D> dtos);
}
