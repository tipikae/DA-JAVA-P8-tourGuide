/**
 * 
 */
package tourGuide.clients;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import feign.Param;
import feign.RequestLine;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;
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
	 * Get an user location.
	 * @param userName String
	 * @return VisitedLocation
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /location/{userName}")
	VisitedLocation getUserLocation(@Param("userName") String userName) 
			throws NotFoundException, BadRequestException, HttpException;
	
	/**
	 * Get all users last location.
	 * @return Map
	 * @throws HttpException
	 */
	@RequestLine("GET /lastlocations")
	Map<UUID, Location> getAllUsersLastLocation() throws HttpException;
	
	/**
	 * Get NearByAttractions of a user.
	 * @param userName String
	 * @return List
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /nearbyattractions/{userName}")
	List<NearByAttraction> getNearByAttractions(@Param("userName") String userName) 
			throws NotFoundException, BadRequestException, HttpException;
}
