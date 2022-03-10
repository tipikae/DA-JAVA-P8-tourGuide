package com.tripmaster.tourguide.tripPricerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TripPricerService main class.
 * @author tipikae
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TripPricerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripPricerServiceApplication.class, args);
	}

}
