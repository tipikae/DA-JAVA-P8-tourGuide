/**
 * 
 */
package com.tripmaster.tourguide.gpsService.model;

import java.util.Date;
import java.util.UUID;

import gpsUtil.location.Location;

/**
 * VisitedLocation model.
 * @author tipikae
 * @version 1.0
 *
 */
public class VisitedLocation extends gpsUtil.location.VisitedLocation {

	public VisitedLocation(UUID userId, Location location, Date timeVisited) {
		super(userId, location, timeVisited);
	}

	
}
