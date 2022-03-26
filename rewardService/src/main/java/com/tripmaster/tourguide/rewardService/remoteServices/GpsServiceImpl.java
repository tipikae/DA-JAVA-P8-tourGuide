/**
 * 
 */
package com.tripmaster.tourguide.rewardService.remoteServices;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.rewardService.clients.IGpsServiceClient;
import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class GpsServiceImpl implements IGpsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GpsServiceImpl.class);
	
	@Autowired
	private IGpsServiceClient gpsClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Attraction> getAttractions() throws HttpException {
		List<Attraction> attractions = new ArrayList<>();
		
		try {
			attractions = gpsClient.getAttractions();
		} catch (Exception e) {
			LOGGER.debug("getAttractions: gpsClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return attractions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VisitedLocation> getUserVisitedLocations(UUID userId) throws HttpException {
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		
		try {
			visitedLocations = gpsClient.getUserVisitedLocations(userId);
		} catch (Exception e) {
			LOGGER.debug("getUserVisitedLocation: gpsClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return visitedLocations;
	}

}
