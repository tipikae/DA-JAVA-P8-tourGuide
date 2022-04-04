/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

/**
 * VisitedLocation-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTOVisitedLocation extends IConverterDTO<MVisitedLocation, VisitedLocationDTO> {

	/**
	 * Convert VisitedLocation List to DTO List.
	 * @param visitedLocations List
	 * @return List
	 * @throws ConverterDTOException
	 */
	List<VisitedLocationDTO> convertVisitedLocationsToDTOs(List<MVisitedLocation> visitedLocations) 
			throws ConverterDTOException;
}
