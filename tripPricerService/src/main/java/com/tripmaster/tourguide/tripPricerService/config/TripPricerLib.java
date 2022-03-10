/**
 * 
 */
package com.tripmaster.tourguide.tripPricerService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tripPricer.TripPricer;

/**
 * Trip Picer library.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class TripPricerLib {

	@Bean
	public TripPricer getTripPricer() {
		return new TripPricer();
	}
}
