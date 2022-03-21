/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.VisitedLocation;

/**
 * VisitedLocation-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTOVisitedLocation extends IConverterDTO<VisitedLocation, VisitedLocationDTO> {

	/**
	 * Convert VisitedLocation List to DTO List.
	 * @param visitedLocations List<VisitedLocation>
	 * @return List<VisitedLocationDTO>
	 * @throws ConverterException
	 */
	List<VisitedLocationDTO> convertVisitedLocationsToDTOs(List<VisitedLocation> visitedLocations) 
			throws ConverterException;
}
