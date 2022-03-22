/**
 * 
 */
package com.tripmaster.tourguide.gpsService.converters;

import java.util.List;

import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;

/**
 * Attraction-DTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTOAttraction extends IConverterDTO<MAttraction, AttractionDTO> {

	/**
	 * Convert Attraction List to DTO List.
	 * @param attractions List<Attraction>
	 * @return List<AttractionDTO>
	 * @throws ConverterDTOException
	 */
	List<AttractionDTO> convertAttractionsToDTos(List<MAttraction> attractions) throws ConverterDTOException;
}
