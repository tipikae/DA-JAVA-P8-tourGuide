/**
 * 
 */
package com.tripmaster.tourguide.gpsService.util;

/**
 * Helper interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IHelper {

	/**
	 * Calculate distance between two points.
	 * @param latitude1 latitude point 1.
	 * @param longitude1 longitude point 1.
	 * @param latitude2 latitude point 2.
	 * @param longitude2 longitude point 2.
	 * @return double
	 */
	double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2);
}
