/**
 * 
 */
package com.tripmaster.tourguide.rewardService.dto;

/**
 * Reward DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class RewardDTO {

	private VisitedLocationDTO visitedLocation;
	private AttractionDTO attraction;
	private int rewardPoints;
	
	public RewardDTO() {
	}

	/**
	 * @return the visitedLocation
	 */
	public VisitedLocationDTO getVisitedLocation() {
		return visitedLocation;
	}

	/**
	 * @param visitedLocation the visitedLocation to set
	 */
	public void setVisitedLocation(VisitedLocationDTO visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	/**
	 * @return the attraction
	 */
	public AttractionDTO getAttraction() {
		return attraction;
	}

	/**
	 * @param attraction the attraction to set
	 */
	public void setAttraction(AttractionDTO attraction) {
		this.attraction = attraction;
	}

	/**
	 * @return the rewardPoints
	 */
	public int getRewardPoints() {
		return rewardPoints;
	}

	/**
	 * @param rewardPoints the rewardPoints to set
	 */
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
}
