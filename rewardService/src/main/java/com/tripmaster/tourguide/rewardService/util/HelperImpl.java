package com.tripmaster.tourguide.rewardService.util;

import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.model.Location;

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
	public double calculateDistance(Location location1, Location location2) {
		double lat1 = Math.toRadians(location1.getLatitude());
        double lon1 = Math.toRadians(location1.getLongitude());
        double lat2 = Math.toRadians(location2.getLatitude());
        double lon2 = Math.toRadians(location2.getLongitude());

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        
        return statuteMiles;
	}

}
