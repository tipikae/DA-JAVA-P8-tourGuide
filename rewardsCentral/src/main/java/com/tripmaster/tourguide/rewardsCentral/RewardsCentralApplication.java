package com.tripmaster.tourguide.rewardsCentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RewardsCentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsCentralApplication.class, args);
	}

}
