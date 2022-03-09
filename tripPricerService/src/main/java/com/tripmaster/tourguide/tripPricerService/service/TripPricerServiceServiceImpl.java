/**
 * 
 */
package com.tripmaster.tourguide.tripPricerService.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tripPricer.Provider;
import tripPricer.TripPricer;

/**
 * Trip Pricer Service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class TripPricerServiceServiceImpl implements ITripPricerServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TripPricerServiceServiceImpl.class);
	
	@Autowired
	private TripPricer tripPricer;
	
	@Value("${trippricer.apikey}")
	private static String apiKey;

	@Override
	public List<Provider> getPrice(UUID userId, int nbAdults, int nbChildren, 
			int tripDuration, int rewardPoints) {
		LOGGER.debug("getPrice: userId=" + userId + ", adults=" + nbAdults + ", children=" + nbChildren
				+ ", duration=" + tripDuration + ", rewardPoints=" + rewardPoints);
		return tripPricer.getPrice(apiKey, userId, nbAdults, nbChildren, tripDuration, rewardPoints);
	}

	@Override
	public String getProviderName(int nbAdults) {
		LOGGER.debug("getProviderName: adults=" + nbAdults);
		LOGGER.debug("getProviderName: apiKey=" + apiKey);
		return tripPricer.getProviderName(apiKey, nbAdults);
	}

}
