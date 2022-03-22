/**
 * 
 */
package com.tripmaster.tourguide.gpsService.model;

/**
 * NearBy Attraction model.
 * @author tipikae
 * @version 1.0
 *
 */
public class NearByAttraction {

	private String attractionName;
	private MLocation attractionLocation;
	private MLocation userLocation;
	private double distance;
	private int points;
	
	public NearByAttraction() {
	}
	
	public NearByAttraction(String attractionName, MLocation attractionLocation, MLocation userLocation,
			double distance, int points) {
		this.attractionName = attractionName;
		this.attractionLocation = attractionLocation;
		this.userLocation = userLocation;
		this.distance = distance;
		this.points = points;
	}

	/**
	 * @return the attractionName
	 */
	public String getAttractionName() {
		return attractionName;
	}

	/**
	 * @param attractionName the attractionName to set
	 */
	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}

	/**
	 * @return the attractionLocation
	 */
	public MLocation getAttractionLocation() {
		return attractionLocation;
	}

	/**
	 * @param attractionLocation the attractionLocation to set
	 */
	public void setAttractionLocation(MLocation attractionLocation) {
		this.attractionLocation = attractionLocation;
	}

	/**
	 * @return the userLocation
	 */
	public MLocation getUserLocation() {
		return userLocation;
	}

	/**
	 * @param userLocation the userLocation to set
	 */
	public void setUserLocation(MLocation userLocation) {
		this.userLocation = userLocation;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
}
