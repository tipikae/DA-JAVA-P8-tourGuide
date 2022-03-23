/**
 * 
 */
package com.tripmaster.tourguide.rewardService.util;

import com.tripmaster.tourguide.rewardService.model.Location;

/**
 * Helper interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IHelper {

	/**
	 * Calculate distance between two points.
	 * @param location1 Location.
	 * @param location2 Location.
	 * @return double
	 */
	double calculateDistance(Location location1, Location location2);
}
