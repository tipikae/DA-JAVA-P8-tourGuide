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

import feign.FeignException;
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
	 * @throws FeignException
	 */
	@GetMapping("/trips/{username}")
	List<Provider> getTripDeals(@PathVariable("username") String username) throws FeignException;
	
	/**
	 * Add an user.
	 * @param newUserDTO NewUserDTO
	 * @return User
	 * @throws FeignException
	 */
	@PostMapping("/user")
	User addUser(@RequestBody NewUserDTO newUserDTO) throws FeignException;
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param newPreferenceDTO NewPreferenceDTO
	 * @throws FeignException
	 */
	@PutMapping("/user/{userName}")
	void updatePreferences(
			@PathVariable("userName") String userName, 
			@RequestBody NewPreferenceDTO newPreferenceDTO) throws FeignException;
	
}
