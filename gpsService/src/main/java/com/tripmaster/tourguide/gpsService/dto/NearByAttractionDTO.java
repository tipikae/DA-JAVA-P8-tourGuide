/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

/**
 * NearBy Attraction DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class NearByAttractionDTO {

	private String attractionName;
	private LocationDTO attractionLocation;
	private LocationDTO userLocation;
	private double distance;
	private int points;
	
	public NearByAttractionDTO() {
	}
	
	public NearByAttractionDTO(String attractionName, LocationDTO attractionLocation, LocationDTO userLocation,
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
	public LocationDTO getAttractionLocation() {
		return attractionLocation;
	}

	/**
	 * @param attractionLocation the attractionLocation to set
	 */
	public void setAttractionLocation(LocationDTO attractionLocation) {
		this.attractionLocation = attractionLocation;
	}

	/**
	 * @return the userLocation
	 */
	public LocationDTO getUserLocation() {
		return userLocation;
	}

	/**
	 * @param userLocation the userLocation to set
	 */
	public void setUserLocation(LocationDTO userLocation) {
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
