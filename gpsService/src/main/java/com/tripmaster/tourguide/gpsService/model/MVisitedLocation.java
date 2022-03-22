/**
 * 
 */
package com.tripmaster.tourguide.gpsService.model;

import java.util.Date;
import java.util.UUID;

/**
 * VisitedLocation model.
 * @author tipikae
 * @version 1.0
 *
 */
public class MVisitedLocation {

	private UUID userId;
	private MLocation location;
	private Date timeVisited;
	
	public MVisitedLocation() {
		
	}
	
	public MVisitedLocation(UUID userId, MLocation location, Date timeVisited) {
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
	public MLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(MLocation location) {
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
