/**
 * 
 */
package tourGuide.model;

import java.util.UUID;

/**
 * VisitedLocation model.
 * @author tipikae
 * @version 1.0
 *
 */
public class VisitedLocation {

	private UUID userId;
	private Location location;
	private String timeVisited;
	
	public VisitedLocation() {
		
	}
	
	public VisitedLocation(UUID userId, Location location, String timeVisited) {
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
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
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
