/**
 * 
 */
package com.tripmaster.tourguide.userService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tripPricer.TripPricer;

/**
 * TripPricer library.
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
