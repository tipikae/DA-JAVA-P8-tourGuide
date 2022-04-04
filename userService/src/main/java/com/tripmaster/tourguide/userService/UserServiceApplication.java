package com.tripmaster.tourguide.userService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import com.tripmaster.tourguide.userService.model.User;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication implements CommandLineRunner {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IUserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// save a user only in dev profile for integration testing with others microservices
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			UUID userId = UUID.fromString("7894208e-d299-4485-85dc-07f19308c1ea");
			User user = new User(userId, "jon", "000", "jon@tourGuide.com");
			userRepository.save(user);
		}
		
	}

}
