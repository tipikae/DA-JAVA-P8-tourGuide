/**
 * 
 */
package com.tripmaster.tourguide.rewardService.dto;

import java.util.List;

/**
 * New VisitedLocations and Attractions DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewVisitedLocationsAndAttractionsDTO {

	private List<VisitedLocationDTO> visitedLocations;
	private List<AttractionDTO> attractions;
	
	public NewVisitedLocationsAndAttractionsDTO() {
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
