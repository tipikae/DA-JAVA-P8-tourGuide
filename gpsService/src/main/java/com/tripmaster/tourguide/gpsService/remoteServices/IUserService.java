/**
 * 
 */
package com.tripmaster.tourguide.gpsService.remoteServices;

import java.util.List;
import java.util.UUID;

import com.tripmaster.tourguide.gpsService.exceptions.HttpBadRequestException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpUserNotFoundException;
import com.tripmaster.tourguide.gpsService.model.User;

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
	 * @throws HttpBadRequestException 
	 * @throws HttpUserNotFoundException 
	 * @throws HttpException 
	 */
	User getUser(String userName) throws HttpBadRequestException, HttpUserNotFoundException, HttpException;
	
	/**
	 * Get a user id.
	 * @param userName String
	 * @return UUID
	 * @throws HttpBadRequestException 
	 * @throws HttpUserNotFoundException 
	 * @throws HttpException 
	 */
	UUID getUserId(String userName) throws HttpBadRequestException, HttpUserNotFoundException, HttpException;

	/**
	 * Get all userIds.
	 * @return List
	 * @throws HttpBadRequestException 
	 * @throws HttpException 
	 */
	List<UUID> getAllUserIds() throws HttpBadRequestException, HttpException;
}
