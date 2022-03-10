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

import tourGuide.model.Provider;

/**
 * Feign client for TripPricerService.
 * @author tipikae
 * @version 1.0
 *
 */
@FeignClient("TripPricerService")
public interface ITripPricerServiceClient {

	/**
	 * Get price offers.
	 * @param userID
	 * @param nbAdults
	 * @param nbChildren
	 * @param tripDuration
	 * @param rewardPoints
	 * @return List<Provider>
	 */
	@RequestMapping(value = "/trippricer/price", method = RequestMethod.GET)
	List<Provider> getPrice(@RequestParam("userId") UUID userID, 
			@RequestParam("nbAdults") int nbAdults, 
			@RequestParam("nbChildren") int nbChildren, 
			@RequestParam("tripDuration") int tripDuration, 
			@RequestParam("rewardPoints") int rewardPoints);
	
	/**
	 * Get provider name.
	 * @param adults
	 * @return String
	 */
	@RequestMapping(value = "/trippricer/provider", method = RequestMethod.GET)
	String getProviderName(@RequestParam("adults") int adults);
}
