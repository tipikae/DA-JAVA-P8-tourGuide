/**
 * 
 */
package com.tripmaster.tourguide.rewardService.model;

/**
 * Reward model.
 * @author tipikae
 * @version 1.0
 *
 */
public class Reward {

	private VisitedLocation visitedLocation;
	private Attraction attraction;
	private int rewardPoints;
	
	public Reward() {
		
	}
	
	public Reward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}
	
	public Reward(VisitedLocation visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	/**
	 * @return the visitedLocation
	 */
	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}

	/**
	 * @param visitedLocation the visitedLocation to set
	 */
	public void setVisitedLocation(VisitedLocation visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	/**
	 * @return the attraction
	 */
	public Attraction getAttraction() {
		return attraction;
	}

	/**
	 * @param attraction the attraction to set
	 */
	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getRewardPoints() {
		return rewardPoints;
	}
}
