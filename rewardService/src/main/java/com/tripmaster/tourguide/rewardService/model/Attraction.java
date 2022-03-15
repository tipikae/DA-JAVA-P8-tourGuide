/**
 * 
 */
package com.tripmaster.tourguide.rewardService.model;

import java.util.UUID;

/**
 * Attraction model.
 * @author tipikae
 * @version 1.0
 *
 */
public class Attraction extends Location {

	private UUID attractionId;
	private String attractionName;
	private String city;
	private String state;
	
	public Attraction() {
		
	}
	
	public Attraction(UUID attractionId, String attractionName, String city, String state,
			double latitude, double longitude) {
		super(latitude, longitude);
		this.attractionId = attractionId;
		this.attractionName = attractionName;
		this.city = city;
		this.state = state;
	}

	/**
	 * @return the attractionId
	 */
	public UUID getAttractionId() {
		return attractionId;
	}

	/**
	 * @param attractionId the attractionId to set
	 */
	public void setAttractionId(UUID attractionId) {
		this.attractionId = attractionId;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
}
