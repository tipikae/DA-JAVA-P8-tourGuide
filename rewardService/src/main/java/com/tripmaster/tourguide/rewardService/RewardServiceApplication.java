package com.tripmaster.tourguide.rewardService;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import com.tripmaster.tourguide.rewardService.model.Attraction;
import com.tripmaster.tourguide.rewardService.model.Location;
import com.tripmaster.tourguide.rewardService.model.Reward;
import com.tripmaster.tourguide.rewardService.model.VisitedLocation;
import com.tripmaster.tourguide.rewardService.repository.IRewardRepository;

/**
 * RewardService main class.
 * @author tipikae
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableFeignClients
public class RewardServiceApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceApplication.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IRewardRepository rewardRepository;
	
	@PostConstruct
	public void init() {
		Locale.setDefault(Locale.UK);
		LOGGER.debug("environment reward.proximityBuffer = " + env.getProperty("reward.proximityBuffer"));
	}

	public static void main(String[] args) {
		SpringApplication.run(RewardServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// save a reward only in dev profile for integration testing with others microservices
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			UUID userId = UUID.fromString("7894208e-d299-4485-85dc-07f19308c1ea");
			Location location = new Location(
					10d, 
					20d);
			VisitedLocation visitedLocation = new VisitedLocation(
					userId, 
					location, 
					new Date());
			Attraction attraction = new Attraction(
					UUID.fromString("639483b3-4241-41fc-b673-75463d228154"), 
					"Disneyland", 
					"Anaheim", 
					"CA", 
					33.817595, 
					-117.922008);
			Reward reward = new Reward(
					visitedLocation, 
					attraction, 
					100);
			rewardRepository.save(reward);
		}
	}

}
