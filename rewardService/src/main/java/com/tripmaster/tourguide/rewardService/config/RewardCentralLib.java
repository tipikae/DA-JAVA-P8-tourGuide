/**
 * 
 */
package com.tripmaster.tourguide.rewardService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rewardCentral.RewardCentral;

/**
 * RewardCentral library bean.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class RewardCentralLib {

	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}
}
