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

import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.model.Provider;
import tourGuide.model.User;

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
	 * @param newUserDTO NewUserDTO
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/user")
	User addUser(@RequestBody NewUserDTO newUserDTO);
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param newPreferenceDTO NewPreferenceDTO
	 */
	@PutMapping("/user/{userName}")
	void updatePreferences(
			@PathVariable("userName") String userName, 
			@RequestBody NewPreferenceDTO newPreferenceDTO);
	
}
