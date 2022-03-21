/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converterDTO;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterException;
import com.tripmaster.tourguide.gpsService.model.Attraction;

/**
 * Attraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTOAttraction extends IConverterDTO<Attraction, AttractionDTO> {

	/**
	 * Convert Attraction List to DTO List.
	 * @param attractions List<Attraction>
	 * @return List<AttractionDTO>
	 * @throws ConverterException
	 */
	List<AttractionDTO> convertAttractionsToDTos(List<Attraction> attractions) throws ConverterException;
}
