package com.tripmaster.tourguide.gpsUtil;

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
public class GpsUtilApplication {
	
	@PostConstruct
	public void init() {
		Locale.setDefault(Locale.UK);
	}

	public static void main(String[] args) {
		SpringApplication.run(GpsUtilApplication.class, args);
	}

}
