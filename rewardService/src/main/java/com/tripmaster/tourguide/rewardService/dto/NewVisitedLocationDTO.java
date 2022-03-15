/**
 * 
 */
package com.tripmaster.tourguide.rewardService.dto;

import java.util.Date;
import java.util.UUID;

/**
 * New VisitedLocationDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewVisitedLocationDTO {

	private UUID userId;
	private NewLocationDTO location;
	private Date timeVisited;
	
	public NewVisitedLocationDTO() {
		
	}

	public NewVisitedLocationDTO(UUID userId, NewLocationDTO location, Date timeVisited) {
		this.userId = userId;
		this.location = location;
		this.timeVisited = timeVisited;
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
	public NewLocationDTO getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(NewLocationDTO location) {
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
