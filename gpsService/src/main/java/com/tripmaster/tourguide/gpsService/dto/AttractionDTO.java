/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

import java.util.UUID;

/**
 * Attraction DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class AttractionDTO extends LocationDTO {

	private UUID attractionId;
	private String attractionName;
	private String city;
	private String state;
	
	public AttractionDTO() {
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
