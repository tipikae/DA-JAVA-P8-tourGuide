/**
 * 
 */
package com.tripmaster.tourguide.rewardService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.rewardService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.rewardService.exceptions.ConverterException;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

/**
 * VisitedLocation-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IVisitedLocationConverterDTO extends IConverter<VisitedLocation, VisitedLocationDTO> {

	/**
	 * Convert a VisitedLocationDTO to a VisitedLocation.
	 * @param dto VisitedLocationDTO
	 * @return VisitedLocation
	 * @throws ConverterException 
	 */
	VisitedLocation convertDTOToEntity(VisitedLocationDTO dto) throws ConverterException;
	
	/**
	 * Convert a VisitedLocationDTOs list to a VisitedLocations list.
	 * @param dtos List<VisitedLocationDTO>
	 * @return List<VisitedLocation>
	 * @throws ConverterException 
	 */
	List<VisitedLocation> convertDTOsToEntities(List<VisitedLocationDTO> dtos) throws ConverterException;
}
