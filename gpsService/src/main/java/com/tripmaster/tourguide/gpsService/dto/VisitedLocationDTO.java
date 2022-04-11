/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * VisitedLocation DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class VisitedLocationDTO {

	@NotNull
	private UUID userId;
	@NotNull
	private LocationDTO location;
	@NotNull
	private String timeVisited;
	
	public VisitedLocationDTO() {
	}

	public VisitedLocationDTO(@NotNull UUID userId, @NotNull LocationDTO location, @NotNull String timeVisited) {
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
	public String getTimeVisited() {
		return timeVisited;
	}

	/**
	 * @param timeVisited the timeVisited to set
	 */
	public void setTimeVisited(String timeVisited) {
		this.timeVisited = timeVisited;
	}
	
	
}
