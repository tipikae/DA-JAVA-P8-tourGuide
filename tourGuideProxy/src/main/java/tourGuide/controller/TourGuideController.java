package tourGuide.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.exception.HttpException;
import tourGuide.model.Location;
import tourGuide.model.NearByAttraction;
import tourGuide.model.Provider;
import tourGuide.model.Reward;
import tourGuide.model.User;
import tourGuide.model.VisitedLocation;

/**
 * Proxy controller for TourGuide application.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
public class TourGuideController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TourGuideController.class);
	
	@Autowired
	private IUserServiceClient userClient;
	
	@Autowired
	private IGpsServiceClient gpsClient;
	
	@Autowired
	private IRewardServiceClient rewardClient;

	/**
	 * Get application home.
	 * @return String
	 */
	@ApiOperation("Get home.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = String.class)
	})
	@GetMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
    
	/**
	 * Get an user's location.
	 * @param userName String
	 * @return VisitedLocation
	 * @throws HttpException 
	 */
	@ApiOperation("Get an user's location.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = VisitedLocation.class),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 404, message = "User not found.")
	})
    @GetMapping("/getLocation") 
    public VisitedLocation getLocation(@RequestParam String userName) throws HttpException {
    	LOGGER.info("getLocation: userName=" + userName);
    	return gpsClient.getUserLocation(userName);
    }
   
    /**
     * Get an user's nearby attractions.
     * @param userName String
     * @return List
     * @throws HttpException 
     */
	@ApiOperation("Get an user's nearby attractions.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = List.class),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 404, message = "User not found.")
	})
    @GetMapping("/getNearbyAttractions") 
    public List<NearByAttraction> getNearbyAttractions(@RequestParam String userName) throws HttpException {
    	LOGGER.info("getNearbyAttractions: userName=" + userName);
		return gpsClient.getNearByAttractions(userName);
    }
    
    /**
     * Get an user's rewards.
     * @param userName String
     * @return List
     * @throws HttpException 
     */
	@ApiOperation("Get an user's rewards.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = List.class),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 404, message = "User not found.")
	})
    @GetMapping("/getRewards") 
    public List<Reward> getRewards(@RequestParam String userName) throws HttpException {
    	LOGGER.info("getRewards: userName=" + userName);
		return rewardClient.getUserRewards(userName);
    }
    
    /**
     * Get all current users' location.
     * @return Map
     * @throws HttpException 
     */
	@ApiOperation("Get all current users' location.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = Map.class),
		@ApiResponse(code = 400, message = "Bad request.")
	})
    @GetMapping("/getAllCurrentLocations")
    public Map<UUID, Location> getAllCurrentLocations() throws HttpException {
    	LOGGER.info("getAllCurrentLocations");
		return gpsClient.getAllUsersLastLocation();
    }
    
    /**
     * Get an user's trip deals.
     * @param userName String
     * @return List
     * @throws HttpException 
     */
	@ApiOperation("Get an user's trip deals.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = List.class),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 404, message = "User not found.")
	})
    @GetMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName) throws HttpException {
    	LOGGER.info("getTripDeals: userName=" + userName);
		return userClient.getTripDeals(userName);
    }
    
    /**
     * Add an user.
     * @param newUserDTO NewUserDTO
     * @return User
     * @throws HttpException 
     */
	@ApiOperation("Add an user.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = User.class),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 405, message = "User already exists.")
	})
    @PostMapping(value = "/addUser", consumes = {"application/json"})
    public User addUser(@RequestBody NewUserDTO newUserDTO) throws HttpException {
    	LOGGER.info("addUser: userName=" + newUserDTO.getUserName());
		return userClient.addUser(newUserDTO);
    }
    
    /**
     * Update an user's preferences.
     * @param userName String
     * @param newPreferenceDTO NewPreferenceDTO
     * @return Object
     * @throws HttpException 
     */
	@ApiOperation("Update an user's preferences.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request."),
		@ApiResponse(code = 404, message = "User not found.")
	})
    @PutMapping(value = "/updateUserPreferences/{userName}", consumes = {"application/json"})
    public int updateUserPreferences(
    		@PathVariable String userName,
    		@RequestBody NewPreferenceDTO newPreferenceDTO) throws HttpException {
    	LOGGER.info("updateUserPreferences: userName=" + userName);
		userClient.updatePreferences(userName, newPreferenceDTO);
    	return 1;
    }

}