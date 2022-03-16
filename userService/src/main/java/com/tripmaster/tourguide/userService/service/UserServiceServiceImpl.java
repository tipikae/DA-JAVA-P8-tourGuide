/**
 * 
 */
package com.tripmaster.tourguide.userService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tripmaster.tourguide.userService.repository.IUserRepository;

import tripPricer.TripPricer;

/**
 * UserService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class UserServiceServiceImpl implements IUserServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceServiceImpl.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private TripPricer tripPricer;
	
	@Value("${trippricer.apikey}")
	private static String apiKey;
	
	
}
