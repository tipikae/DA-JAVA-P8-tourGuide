package com.tripmaster.tourguide.gpsService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tripmaster.tourguide.gpsService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.gpsService.clients.IUserServiceClient;
import com.tripmaster.tourguide.gpsService.exceptions.MyFeignErrorDecoder;

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

	@Bean
	public IUserServiceClient getUserServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IUserServiceClient.class, "http://localhost:8082/userservice");
	}

	@Bean
	public IRewardServiceClient getRewardServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IRewardServiceClient.class, "http://localhost:8083/rewardservice");
	}
}
