/**
 * 
 */
package tourGuide.clients;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import feign.FeignException;
import feign.Param;
import feign.RequestLine;
import tourGuide.model.Attraction;
import tourGuide.model.Location;
import tourGuide.model.NearByAttraction;
import tourGuide.model.VisitedLocation;

/**
 * Feign client for GpsService.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IGpsServiceClient {

	/**
	 * Get all attractions.
	 * @return List
	 * @throws FeignException
	 */
	@RequestLine("GET /attractions")
	List<Attraction> getAttractions() throws FeignException;
	
	/**
	 * Get an user location.
	 * @param userName String
	 * @return VisitedLocation
	 * @throws FeignException
	 */
	@RequestLine("GET /location/{userName}")
	VisitedLocation getUserLocation(@Param("userName") String userName) throws FeignException;
	
	/**
	 * Get all users last location.
	 * @return Map
	 * @throws FeignException
	 */
	@RequestLine("GET /lastlocations")
	Map<UUID, Location> getAllUsersLastLocation() throws FeignException;
	
	/**
	 * Get NearByAttractions of a user.
	 * @param userName String
	 * @return List
	 * @throws FeignException
	 */
	@RequestLine("GET /nearbyattractions/{userName}")
	List<NearByAttraction> getNearByAttractions(@Param("userName") String userName) 
			throws FeignException;
}
