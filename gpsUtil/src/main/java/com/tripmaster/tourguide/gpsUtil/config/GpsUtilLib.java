/**
 * 
 */
package com.tripmaster.tourguide.gpsUtil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;

/**
 * Bean for gpsUtil.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class GpsUtilLib {

	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}
}
