/**
 * 
 */
package tourGuide.clients;

import feign.FeignException;
import feign.Param;
import feign.RequestLine;

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
	 * @throws FeignException
	 */
	@RequestLine("GET /location/{userName}")
	Object getUserLocation(@Param("userName") String userName) throws FeignException;
	
	/**
	 * Get all users last location.
	 * @return Object
	 * @throws FeignException
	 */
	@RequestLine("GET /lastlocations")
	Object getAllUsersLastLocation() throws FeignException;
	
	/**
	 * Get NearByAttractions of a user.
	 * @param userName String
	 * @return Object
	 * @throws FeignException
	 */
	@RequestLine("GET /nearbyattractions/{userName}")
	Object getNearByAttractions(@Param("userName") String userName) 
			throws FeignException;
}
