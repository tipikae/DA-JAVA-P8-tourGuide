package tourGuide;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;

import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.service.TourGuideService;

@RestController
public class TourGuideController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TourGuideController.class);

	@Autowired
	TourGuideService tourGuideService;
	
	@Autowired
	private IUserServiceClient userClient;
	
	@Autowired
	private IGpsServiceClient gpsClient;
	
	@Autowired
	private IRewardServiceClient rewardClient;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
    
    @RequestMapping("/getLocation") 
    public String getLocation(@RequestParam String userName) {
    	LOGGER.info("getLocation: userName=" + userName);
		return JsonStream.serialize(gpsClient.getUserLocation(userName));
    }
    
    //  TODO: Change this method to no longer return a List of Attractions.
 	//  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
 	//  Return a new JSON object that contains:
    	// Name of Tourist attraction, 
        // Tourist attractions lat/long, 
        // The user's location lat/long, 
        // The distance in miles between the user's location and each of the attractions.
        // The reward points for visiting each Attraction.
        //    Note: Attraction reward points can be gathered from RewardsCentral
    @RequestMapping("/getNearbyAttractions") 
    public String getNearbyAttractions(@RequestParam String userName) {
    	LOGGER.info("getNearbyAttractions: userName=" + userName);
    	return JsonStream.serialize(gpsClient.getNearByAttractions(userName));
    }
    
    @RequestMapping("/getRewards") 
    public String getRewards(@RequestParam String userName) {
    	LOGGER.info("getRewards: userName=" + userName);
    	return JsonStream.serialize(rewardClient.getUserRewards(userName));
    }
    
    @RequestMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
    	// TODO: Get a list of every user's most recent location as JSON
    	//- Note: does not use gpsUtil to query for their current location, 
    	//        but rather gathers the user's current location from their stored location history.
    	//
    	// Return object should be the just a JSON mapping of userId to Locations similar to:
    	//     {
    	//        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371} 
    	//        ...
    	//     }
    	LOGGER.info("getAllCurrentLocations");
    	return JsonStream.serialize(gpsClient.getAllUsersLastLocation());
    }
    
    @RequestMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName) {
    	LOGGER.info("getTripDeals: userName=" + userName);
    	return JsonStream.serialize(userClient.getTripDeals(userName));
    }
    
    /**
     * Add a user.
     * @param user User
     * @return String
     */
    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid NewUserDTO newUserDTO) {
    	LOGGER.info("addUser: userName=" + newUserDTO.getUserName());
		return JsonStream.serialize(userClient.addUser(newUserDTO));
    }
    
    /**
     * Update an user's preferences.
     * @param userName String
     * @param preference UserPreference
     * @return String
     */
    @PutMapping("/updateUserPreferences/{userName}")
    public String updateUserPreferences(
    		@PathVariable String userName,
    		@RequestBody @Valid NewPreferenceDTO newPreferenceDTO) {
    	LOGGER.info("updateUserPreferences: userName=" + userName);
    	userClient.updatePreferences(userName, newPreferenceDTO);
    	return "";
    }

}