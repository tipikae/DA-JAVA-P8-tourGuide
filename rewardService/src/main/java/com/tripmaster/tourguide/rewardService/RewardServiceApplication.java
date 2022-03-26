package com.tripmaster.tourguide.rewardService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * RewardService main class.
 * @author tipikae
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableFeignClients
public class RewardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardServiceApplication.class, args);
	}

}
