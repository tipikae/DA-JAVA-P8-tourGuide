package com.tripmaster.tourguide.gpsService;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GpsUtil main class.
 * @author tipikae
 * @version 1.0
 *
 */
@SpringBootApplication
public class GpsServiceApplication {
	
	@PostConstruct
	public void init() {
		Locale.setDefault(Locale.UK);
	}

	public static void main(String[] args) {
		SpringApplication.run(GpsServiceApplication.class, args);
	}

}
