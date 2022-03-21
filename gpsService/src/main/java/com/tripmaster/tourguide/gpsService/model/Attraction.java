/**
 * 
 */
package com.tripmaster.tourguide.gpsService.model;

/**
 * Attraction model.
 * @author tipikae
 * @version 1.0
 *
 */
public class Attraction extends gpsUtil.location.Attraction {

	public Attraction(String attractionName, String city, String state, double latitude, double longitude) {
		super(attractionName, city, state, latitude, longitude);
	}

}
