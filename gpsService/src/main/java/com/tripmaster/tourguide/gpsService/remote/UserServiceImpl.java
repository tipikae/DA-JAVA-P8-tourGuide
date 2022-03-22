/**
 * 
 */
package com.tripmaster.tourguide.gpsService.remote;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.clients.IUserServiceClient;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.model.User;

/**
 * UserService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserServiceClient userClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String userName) throws HttpException {
		User user = null;
		
		try {
			user = userClient.getUser(userName);
		} catch (Exception e) {
			LOGGER.debug("getUser: userClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID getUserId(String userName) throws HttpException {
		UUID userId = null;
		
		try {
			userId = userClient.getUserId(userName);
		} catch (Exception e) {
			LOGGER.debug("getUserId: userClient error: " + e.getClass().getSimpleName() 
					+ ": " + e.getMessage());
			throw new HttpException(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		return userId;
	}

}
