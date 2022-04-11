/**
 * 
 */
package tourGuide.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.exception.MyFeignErrorDecoder;

/**
 * Http clients configuration for feign
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpClientConfig {
	
	@Value(value = "${client.rewardService.url:}")
	private String rewardServiceUrl;
	
	@Value(value = "${client.userService.url:}")
	private String userServiceUrl;
	
	@Value(value = "${client.gpsService.url:}")
	private String gpsServiceUrl;

	@Bean
	public IRewardServiceClient getRewardServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IRewardServiceClient.class, rewardServiceUrl);
	}

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
