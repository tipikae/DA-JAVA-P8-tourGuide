/**
 * 
 */
package tourGuide.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tourGuide.user.User;
import tourGuide.user.UserPreferences;
import tripPricer.Provider;

/**
 * Feign client for UserService
 * @author tipikae
 * @version 1.0
 *
 */
@FeignClient(name = "UserService", url = "http://localhost:8082/userservice")
public interface IUserServiceClient {

	/**
	 * Get an users's trip deals.
	 * @param username String
	 * @return List<Provider>
	 */
	@GetMapping("/trips/{username}")
	List<Provider> getTripDeals(@PathVariable("username") String username);
	
	/**
	 * Add a user.
	 * @param user User
	 * @return User
	 */
	@PostMapping("/user")
	User addUser(@RequestBody User user);
	
	/**
	 * Update an user's preferences.
	 * @param username String
	 * @param preference UserPreference
	 */
	@PutMapping("/user/{username}")
	void updatePreferences(@PathVariable("username") String username, 
			@RequestBody UserPreferences preference);
	
}
