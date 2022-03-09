/**
 * 
 */
package com.tripmaster.tourguide.tripPricerService.service;

import java.util.List;
import java.util.UUID;

import tripPricer.Provider;

/**
 * TripPricer Service interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface ITripPricerServiceService {

	/**
	 * Get price according user prederences.
	 * @param apiKey
	 * @param userId
	 * @param nbAdults
	 * @param nbChildren
	 * @param tripDuration
	 * @param rewardPoints
	 * @return List<Provider>
	 */
	List<Provider> getPrice(String apiKey, UUID userId, int nbAdults, int nbChildren, 
			int tripDuration, int rewardPoints);
	
	/**
	 * Get provider name.
	 * @param apiKey
	 * @param nbAdults
	 * @return String
	 */
	String getProviderName(String apiKey, int nbAdults);
}
