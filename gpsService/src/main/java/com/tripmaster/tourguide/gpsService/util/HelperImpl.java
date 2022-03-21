package com.tripmaster.tourguide.gpsService.util;

import org.springframework.stereotype.Component;

/**
 * Helper class.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class HelperImpl implements IHelper {
	
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double lat1 = Math.toRadians(latitude1);
        double lon1 = Math.toRadians(longitude1);
        double lat2 = Math.toRadians(latitude2);
        double lon2 = Math.toRadians(longitude2);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        
        return statuteMiles;
	}

}
