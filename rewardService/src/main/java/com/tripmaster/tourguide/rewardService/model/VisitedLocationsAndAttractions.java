/**
 * 
 */
package com.tripmaster.tourguide.rewardService.model;

import java.util.List;

/**
 * VisitedLocations and Attractions structure.
 * @author tipikae
 * @version 1.0
 *
 */
public class VisitedLocationsAndAttractions {

	private List<VisitedLocation> visitedLocations;
	private List<Attraction> attractions;
	
	public VisitedLocationsAndAttractions() {
		
	}
	
	/**
	 * @return the visitedLocations
	 */
	public List<VisitedLocation> getVisitedLocations() {
		return visitedLocations;
	}
	/**
	 * @param visitedLocations the visitedLocations to set
	 */
	public void setVisitedLocations(List<VisitedLocation> visitedLocations) {
		this.visitedLocations = visitedLocations;
	}
	/**
	 * @return the attractions
	 */
	public List<Attraction> getAttractions() {
		return attractions;
	}
	/**
	 * @param attractions the attractions to set
	 */
	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}
	
}
