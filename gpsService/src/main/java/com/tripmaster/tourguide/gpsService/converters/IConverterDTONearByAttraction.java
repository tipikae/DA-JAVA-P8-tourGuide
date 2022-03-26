/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.NearByAttraction;

/**
 * NearByAttraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTONearByAttraction 
	extends IConverterDTO<NearByAttraction, NearByAttractionDTO> {

	/**
	 * Converter NearByAttraction List to DTO List.
	 * @param nearByAttractions List<NearByAttraction>
	 * @return List<NearByAttractionDTO>
	 * @throws ConverterDTOException
	 */
	List<NearByAttractionDTO> convertNearByAttractionsToDTOs(List<NearByAttraction> nearByAttractions) 
			throws ConverterDTOException;
}
