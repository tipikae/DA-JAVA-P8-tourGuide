/**
 * 
 */
package tourGuide.clients;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.FeignException;
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
@FeignClient(name = "GpsService", url = "http://localhost:8081/gpsservice")
public interface IGpsServiceClient {

	/**
	 * Get all attractions.
	 * @return List
	 * @throws FeignException
	 */
	@GetMapping("/attractions")
	List<Attraction> getAttractions() throws FeignException;
	
	/**
	 * Get an user location.
	 * @param userName String
	 * @return VisitedLocation
	 * @throws FeignException
	 */
	@GetMapping("/location/{userName}")
	VisitedLocation getUserLocation(@PathVariable("userName") String userName) throws FeignException;
	
	/**
	 * Get all users last location.
	 * @return Map
	 * @throws FeignException
	 */
	@GetMapping("/lastlocations")
	Map<UUID, Location> getAllUsersLastLocation() throws FeignException;
	
	/**
	 * Get NearByAttractions of a user.
	 * @param userName String
	 * @return List
	 * @throws FeignException
	 */
	@GetMapping("/nearbyattractions/{userName}")
	List<NearByAttraction> getNearByAttractions(@PathVariable("userName") String userName) 
			throws FeignException;
}
