package tourGuide.controller;

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

import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;

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
	 * Get home.
	 * @return String
	 */
	@GetMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
    
	/**
	 * Get an user's location.
	 * @param userName String
	 * @return Object
	 */
    @GetMapping("/getLocation") 
    public Object getLocation(@RequestParam String userName) {
    	LOGGER.info("getLocation: userName=" + userName);
		try {
			return gpsClient.getUserLocation(userName);
		} catch (Exception e) {
			return e.getMessage();
		}
    }
   
    /**
     * Get an user's nearby attractions.
     * @param userName String
     * @return Object
     */
    @GetMapping("/getNearbyAttractions") 
    public Object getNearbyAttractions(@RequestParam String userName) {
    	LOGGER.info("getNearbyAttractions: userName=" + userName);
    	try {
			return gpsClient.getNearByAttractions(userName);
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
     * Get an user's rewards.
     * @param userName String
     * @return Object
     */
    @GetMapping("/getRewards") 
    public Object getRewards(@RequestParam String userName) {
    	LOGGER.info("getRewards: userName=" + userName);
    	try {
			return rewardClient.getUserRewards(userName);
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
     * Get all current users' location.
     * @return Object
     */
    @GetMapping("/getAllCurrentLocations")
    public Object getAllCurrentLocations() {
    	LOGGER.info("getAllCurrentLocations");
    	try {
			return gpsClient.getAllUsersLastLocation();
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
     * Get an user's trip deals.
     * @param userName String
     * @return Object
     */
    @GetMapping("/getTripDeals")
    public Object getTripDeals(@RequestParam String userName) {
    	LOGGER.info("getTripDeals: userName=" + userName);
    	try {
			return userClient.getTripDeals(userName);
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
     * Add an user.
     * @param user User
     * @return Object
     */
    @PostMapping(value = "/addUser", consumes = {"application/json"})
    public Object addUser(@RequestBody NewUserDTO newUserDTO) {
    	LOGGER.info("addUser: userName=" + newUserDTO.getUserName());
    	try {
    		return userClient.addUser(newUserDTO);
		} catch (Exception e) {
			return e.getMessage();
		}
    }
    
    /**
     * Update an user's preferences.
     * @param userName String
     * @param newPreferenceDTO NewPreferenceDTO
     * @return Object
     */
    @PutMapping(value = "/updateUserPreferences/{userName}", consumes = {"application/json"})
    public Object updateUserPreferences(
    		@PathVariable String userName,
    		@RequestBody NewPreferenceDTO newPreferenceDTO) {
    	LOGGER.info("updateUserPreferences: userName=" + userName);
    	try {
			userClient.updatePreferences(userName, newPreferenceDTO);
	    	return "";
		} catch (Exception e) {
			return e.getMessage();
		}
    }

}