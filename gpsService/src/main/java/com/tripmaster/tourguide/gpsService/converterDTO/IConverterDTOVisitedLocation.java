/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
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
	 * @param visitedLocations List<VisitedLocation>
	 * @return List<VisitedLocationDTO>
	 * @throws ConverterException
	 */
	List<VisitedLocationDTO> convertVisitedLocationsToDTOs(List<MVisitedLocation> visitedLocations) 
			throws ConverterException;
}
