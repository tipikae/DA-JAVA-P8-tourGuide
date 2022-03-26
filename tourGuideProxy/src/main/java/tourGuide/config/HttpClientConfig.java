package tourGuide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import tourGuide.clients.IUserServiceClient;

/**
* Configuration for Http clients.
* @author tipikae
* @version 1.0
*
*/
@Configuration
public class HttpClientConfig {

	/*@Bean
	public IUserServiceClient getUserServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				//.errorDecoder(new MyFeignErrorDecoder())
				.client(new OkHttpClient())
				.target(IUserServiceClient.class, "http://localhost:8082/userservice");
	}*/
}
