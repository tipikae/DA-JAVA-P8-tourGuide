/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

import java.util.List;

/**
 * Attractions and VisitedLocations DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class AttractionsAndVisitedLocationsDTO {

	private List<VisitedLocationDTO> visitedLocations;
	private List<AttractionDTO> attractions;
	
	public AttractionsAndVisitedLocationsDTO() {
	}

	/**
	 * @return the visitedLocations
	 */
	public List<VisitedLocationDTO> getVisitedLocations() {
		return visitedLocations;
	}

	/**
	 * @param visitedLocations the visitedLocations to set
	 */
	public void setVisitedLocations(List<VisitedLocationDTO> visitedLocations) {
		this.visitedLocations = visitedLocations;
	}

	/**
	 * @return the attractions
	 */
	public List<AttractionDTO> getAttractions() {
		return attractions;
	}

	/**
	 * @param attractions the attractions to set
	 */
	public void setAttractions(List<AttractionDTO> attractions) {
		this.attractions = attractions;
	}
}
