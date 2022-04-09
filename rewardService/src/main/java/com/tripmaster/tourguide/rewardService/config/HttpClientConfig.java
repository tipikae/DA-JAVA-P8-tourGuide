/**
 * 
 */
package com.tripmaster.tourguide.rewardService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tripmaster.tourguide.rewardService.clients.IGpsServiceClient;
import com.tripmaster.tourguide.rewardService.clients.IUserServiceClient;
import com.tripmaster.tourguide.rewardService.exceptions.MyFeignErrorDecoder;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * HTTP clients configuration.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpClientConfig {
	
	@Value(value = "${client.userService.url:}")
	private String userServiceUrl;
	
	@Value(value = "${client.gpsService.url:}")
	private String gpsServiceUrl;

	@Bean
	public IUserServiceClient getUserServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IUserServiceClient.class, userServiceUrl);
	}

	@Bean
	public IGpsServiceClient getGpsServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IGpsServiceClient.class, gpsServiceUrl);
	}
}
