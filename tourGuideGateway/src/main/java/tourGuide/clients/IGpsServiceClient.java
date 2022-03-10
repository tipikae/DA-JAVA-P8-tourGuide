/**
 * 
 */
package tourGuide.clients;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tourGuide.model.Attraction;
import tourGuide.model.VisitedLocation;

/**
 * Feign client for GpsService.
 * @author tipikae
 * @version 1.0
 *
 */
@FeignClient("GpsService")
public interface IGpsServiceClient {

	/**
	 * Mapping for getAttractions.
	 * @return List<Attraction>
	 */
	@RequestMapping(value = "/gpsservice/attractions", method = RequestMethod.GET)
	List<Attraction> getAttractions();
	
	/**
	 * Mapping for getUserLocation.
	 * @param userId UUID
	 * @return VisitedLocation
	 */
	@RequestMapping(value = "/gpsservice/userlocation", method = RequestMethod.GET)
	VisitedLocation getUserLocation(@RequestParam("userId") UUID userId);
	
	
}
