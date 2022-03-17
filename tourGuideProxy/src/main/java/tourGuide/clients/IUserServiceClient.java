/**
 * 
 */
package tourGuide.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;

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
	Object getTripDeals(@PathVariable("username") String username);
	
	/**
	 * Add a user.
	 * @param newUserDTO NewUserDTO
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/user")
	Object addUser(@RequestBody NewUserDTO newUserDTO);
	
	/**
	 * Update an user's preferences.
	 * @param username String
	 * @param newPreferenceDTO NewPreferenceDTO
	 */
	@PutMapping("/user/{username}")
	void updatePreferences(@PathVariable("username") String username, 
			@RequestBody NewPreferenceDTO newPreferenceDTO);
	
}
