/**
 * 
 */
package com.tripmaster.tourguide.rewardService.dto;

/**
 * New Reward DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewRewardDTO {

	private NewVisitedLocationDTO visitedLocation;
	private NewAttractionDTO attraction;
	private int rewardPoints;
	
	public NewRewardDTO() {
		
	}
	
	public NewRewardDTO(NewVisitedLocationDTO visitedLocation, NewAttractionDTO attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}
	
	public NewRewardDTO(NewVisitedLocationDTO visitedLocation, NewAttractionDTO attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	/**
	 * @return the visitedLocation
	 */
	public NewVisitedLocationDTO getVisitedLocation() {
		return visitedLocation;
	}

	/**
	 * @param visitedLocation the visitedLocation to set
	 */
	public void setVisitedLocation(NewVisitedLocationDTO visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	/**
	 * @return the attraction
	 */
	public NewAttractionDTO getAttraction() {
		return attraction;
	}

	/**
	 * @param attraction the attraction to set
	 */
	public void setAttraction(NewAttractionDTO attraction) {
		this.attraction = attraction;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getRewardPoints() {
		return rewardPoints;
	}

}
