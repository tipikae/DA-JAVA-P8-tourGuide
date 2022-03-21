/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

import java.util.Date;
import java.util.UUID;

/**
 * VisitedLocation DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class VisitedLocationDTO {

	private UUID userId;
	private LocationDTO location;
	private Date timeVisited;
	
	public VisitedLocationDTO() {
	}

	/**
	 * @return the userId
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	/**
	 * @return the location
	 */
	public LocationDTO getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	/**
	 * @return the timeVisited
	 */
	public Date getTimeVisited() {
		return timeVisited;
	}

	/**
	 * @param timeVisited the timeVisited to set
	 */
	public void setTimeVisited(Date timeVisited) {
		this.timeVisited = timeVisited;
	}
	
	
}
