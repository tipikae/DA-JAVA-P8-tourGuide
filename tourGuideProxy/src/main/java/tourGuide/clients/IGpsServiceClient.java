/**
 * 
 */
package tourGuide.clients;

import feign.Param;
import feign.RequestLine;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;

/**
 * Feign client for GpsService.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceClient {
	
	/**
	 * Get an user location.
	 * @param userName String
	 * @return Object
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /location/{userName}")
	Object getUserLocation(@Param("userName") String userName) 
			throws NotFoundException, BadRequestException, HttpException;
	
	/**
	 * Get all users last location.
	 * @return Object
	 * @throws HttpException
	 */
	@RequestLine("GET /lastlocations")
	Object getAllUsersLastLocation() throws HttpException;
	
	/**
	 * Get NearByAttractions of a user.
	 * @param userName String
	 * @return Object
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /nearbyattractions/{userName}")
	Object getNearByAttractions(@Param("userName") String userName) 
			throws NotFoundException, BadRequestException, HttpException;
}
