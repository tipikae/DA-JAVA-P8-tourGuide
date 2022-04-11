package com.tripmaster.tourguide.gpsService.config;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value(value = "${client.userService.url:}")
	private String userServiceUrl;
	
	@Value(value = "${client.rewardService.url:}")
	private String rewardServiceUrl;

	@Bean
	public IUserServiceClient getUserServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IUserServiceClient.class, userServiceUrl);
	}

	@Bean
	public IRewardServiceClient getRewardServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IRewardServiceClient.class, rewardServiceUrl);
	}
}
