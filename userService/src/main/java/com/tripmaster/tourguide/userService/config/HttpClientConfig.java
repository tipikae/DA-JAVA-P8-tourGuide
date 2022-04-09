/**
 * 
 */
package com.tripmaster.tourguide.userService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tripmaster.tourguide.userService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.userService.exceptions.MyFeignErrorDecoder;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * Configuration for Http clients.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpClientConfig {
	
	@Value(value = "${client.rewardService.url:}")
	private String url;

	@Bean
	public IRewardServiceClient getRewardServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IRewardServiceClient.class, url);
	}
}
