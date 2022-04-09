/**
 * 
 */
package tourGuide.clients;

import feign.FeignException;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;

/**
 * Feign client for UserService
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUserServiceClient {

	/**
	 * Get an users's trip deals.
	 * @param username String
	 * @return Object
	 * @throws FeignException
	 */
	@RequestLine("GET /trips/{username}")
	Object getTripDeals(@Param("username") String username) throws FeignException;
	
	/**
	 * Add an user.
	 * @param newUserDTO NewUserDTO
	 * @return Object
	 * @throws FeignException
	 */
	@RequestLine("POST /user")
    @Headers("Content-Type: application/json")
	Object addUser(NewUserDTO newUserDTO) throws FeignException;
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param newPreferenceDTO NewPreferenceDTO
	 * @throws FeignException
	 */
	@RequestLine("PUT /user/{userName}")
    @Headers("Content-Type: application/json")
	void updatePreferences(
			@Param("userName") String userName, 
			NewPreferenceDTO newPreferenceDTO) throws FeignException;
	
}
