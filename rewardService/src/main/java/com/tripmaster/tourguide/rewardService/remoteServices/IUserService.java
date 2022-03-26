package com.tripmaster.tourguide.rewardService.remoteServices;

import java.util.UUID;

import com.tripmaster.tourguide.rewardService.exceptions.HttpException;
import com.tripmaster.tourguide.rewardService.model.User;

/**
 * UserService service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserService {

	/**
	 * Get a user.
	 * @param userName String
	 * @return User
	 * @throws HttpException 
	 */
	User getUser(String userName) throws HttpException;
	
	/**
	 * Get a user id.
	 * @param userName String
	 * @return UUID
	 * @throws HttpException 
	 */
	UUID getUserId(String userName) throws HttpException;
}
