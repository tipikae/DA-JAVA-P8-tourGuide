package tourGuide.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tourGuide.clients.IGpsServiceClient;
import tourGuide.clients.IRewardServiceClient;
import tourGuide.clients.IUserServiceClient;
import tourGuide.controller.TourGuideController;
import tourGuide.dto.NewPreferenceDTO;
import tourGuide.dto.NewUserDTO;
import tourGuide.exception.AlreadyExistException;
import tourGuide.exception.BadRequestException;
import tourGuide.exception.HttpException;
import tourGuide.exception.NotFoundException;
import tourGuide.model.User;
import tourGuide.model.VisitedLocation;

@WebMvcTest(TourGuideController.class)
class TourGuideControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IGpsServiceClient gpsClient;
	
	@MockBean
	private IUserServiceClient userClient;
	
	@MockBean
	private IRewardServiceClient rewardClient;

	@Test
	void index() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string("Greetings from TourGuide!"));
	}

	@Test
	void getLocationReturnsVisitedLocationWhenOk() throws Exception {
		when(gpsClient.getUserLocation(anyString())).thenReturn(new VisitedLocation());
		mockMvc.perform(get("/getLocation")
				.param("userName", "username"))
			.andExpect(status().isOk());
	}

	@Test
	void getLocationReturns404WhenUserNotFoundException() throws Exception {
		doThrow(NotFoundException.class).when(gpsClient).getUserLocation(anyString());
		mockMvc.perform(get("/getLocation")
				.param("userName", "username"))
			.andExpect(status().is(404));
	}

	@Test
	void getNearbyAttractionsReturnsListWhenOk() throws Exception {
		when(gpsClient.getNearByAttractions(anyString())).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/getNearbyAttractions")
				.param("userName", "username"))
			.andExpect(status().isOk());
	}

	@Test
	void getNearbyAttractionsReturns400WhenBadRequestException() throws Exception {
		doThrow(BadRequestException.class).when(gpsClient).getNearByAttractions(anyString());
		mockMvc.perform(get("/getNearbyAttractions")
				.param("userName", "username"))
			.andExpect(status().is(400));
	}

	@Test
	void getRewardsReturnsListWhenOk() throws Exception {
		when(rewardClient.getUserRewards(anyString())).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/getRewards")
				.param("userName", "username"))
			.andExpect(status().isOk());
	}

	@Test
	void getRewardsReturns400WhenHttpException() throws Exception {
		doThrow(HttpException.class).when(rewardClient).getUserRewards(anyString());
		mockMvc.perform(get("/getRewards")
				.param("userName", "username"))
			.andExpect(status().is(400));
	}

	@Test
	void getAllCurrentLocationsReturnsListWhenOk() throws Exception {
		when(gpsClient.getAllUsersLastLocation()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/getAllCurrentLocations"))
			.andExpect(status().isOk());
	}

	@Test
	void getAllCurrentLocationsReturns400WhenHttpException() throws Exception {
		doThrow(HttpException.class).when(gpsClient).getAllUsersLastLocation();
		mockMvc.perform(get("/getAllCurrentLocations"))
			.andExpect(status().is(400));
	}

	@Test
	void getTripDealsReturnsListWhenOk() throws Exception {
		when(userClient.getTripDeals(anyString())).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/getTripDeals")
				.param("userName", "username"))
			.andExpect(status().isOk());
	}

	@Test
	void getTripDealsReturns404WhenNotFoundException() throws Exception {
		doThrow(NotFoundException.class).when(userClient).getTripDeals(anyString());
		mockMvc.perform(get("/getTripDeals")
				.param("userName", "username"))
			.andExpect(status().is(404));
	}

	@Test
	void addUserReturnsUserWhenOk() throws Exception {
		when(userClient.addUser(any(NewUserDTO.class))).thenReturn(new User());
		mockMvc.perform(post("/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"019b04a9-067a-4c76-8817-ee75088c3822\", "
						+ "\"userName\": \"username0\", "
						+ "\"phoneNumber\": \"phone\", "
						+ "\"emailAddress\": \"email\"}"))
			.andExpect(status().isOk());
	}

	@Test
	void addUserReturns405WhenAlreadyExistsException() throws Exception {
		doThrow(AlreadyExistException.class).when(userClient).addUser(any(NewUserDTO.class));
		mockMvc.perform(post("/addUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"019b04a9-067a-4c76-8817-ee75088c3822\", "
						+ "\"userName\": \"username0\", "
						+ "\"phoneNumber\": \"phone\", "
						+ "\"emailAddress\": \"email\"}"))
			.andExpect(status().is(405));
	}

	@Test
	void updateUserPreferencesReturns1WhenOk() throws Exception {
		doNothing().when(userClient).updatePreferences(anyString(), any(NewPreferenceDTO.class));
		mockMvc.perform(put("/updateUserPreferences/username")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tripDuration\": \"10\", "
						+ "\"ticketQuantity\": \"4\", "
						+ "\"numberOfAdults\": \"2\", "
						+ "\"numberOfChildren\": \"2\"}"))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));
	}

	@Test
	void updateUserPreferencesReturns404WhenNotFoundException() throws Exception {
		doThrow(NotFoundException.class)
			.when(userClient).updatePreferences(anyString(), any(NewPreferenceDTO.class));
		mockMvc.perform(put("/updateUserPreferences/username")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tripDuration\": \"10\", "
						+ "\"ticketQuantity\": \"4\", "
						+ "\"numberOfAdults\": \"2\", "
						+ "\"numberOfChildren\": \"2\"}"))
			.andExpect(status().is(404));
	}

}
