package com.tripmaster.tourguide.gpsService;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * GpsUtil main class.
 * @author tipikae
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableFeignClients
public class GpsServiceApplication {
	
	@PostConstruct
	public void init() {
		Locale.setDefault(Locale.US);
	}

	public static void main(String[] args) {
		SpringApplication.run(GpsServiceApplication.class, args);
	}

}
