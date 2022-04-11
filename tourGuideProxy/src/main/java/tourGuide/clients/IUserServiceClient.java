/**
 * 
 */
package tourGuide.clients;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.exception.AlreadyExistException;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;
import tourGuide.model.Provider;
import tourGuide.model.User;

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
	 * @return List
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("GET /trips/{username}")
	List<Provider> getTripDeals(@Param("username") String username) 
			throws NotFoundException, BadRequestException, HttpException;
	
	/**
	 * Add an user.
	 * @param newUserDTO NewUserDTO
	 * @return User
	 * @throws AlreadyExistException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("POST /user")
    @Headers("Content-Type: application/json")
	User addUser(NewUserDTO newUserDTO) 
			throws AlreadyExistException, BadRequestException, HttpException;
	
	/**
	 * Update an user's preferences.
	 * @param userName String
	 * @param newPreferenceDTO NewPreferenceDTO
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpException
	 */
	@RequestLine("PUT /user/{userName}")
    @Headers("Content-Type: application/json")
	void updatePreferences(
			@Param("userName") String userName, 
			NewPreferenceDTO newPreferenceDTO) 
					throws NotFoundException, BadRequestException, HttpException;
	
}
