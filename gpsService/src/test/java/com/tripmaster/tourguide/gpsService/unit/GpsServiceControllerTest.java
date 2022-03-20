package com.tripmaster.tourguide.gpsService.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.gpsService.controller.GpsServiceController;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@WebMvcTest(controllers = GpsServiceController.class)
class GpsServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IGpsServiceService gpsService;
	
	private static String root;
	private static String userName;
	private static UUID userId;
	private static Location location;
	private static VisitedLocation visitedLocation;
	private static List<VisitedLocation> visitedLocations;
	private static Map<UUID, List<VisitedLocation>> map;
	private static Attraction attraction;
	private static List<Attraction> attractions;
	
	@BeforeAll
	private static void setUp() {
		root = "/gpsservice";
		userName = "username";
		userId = UUID.randomUUID();
		location = new Location(10d, 20d);
		visitedLocation = new VisitedLocation(userId, location, new Date());
		visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		map = new HashMap<>();
		map.put(userId, visitedLocations);
		attraction = new Attraction("name", "city", "state", 10d, 20d);
		attractions = new ArrayList<>();
		attractions.add(attraction);
	}

	@Test
	void getAttractionsReturnsJsonListWhenOk() throws Exception {
		when(gpsService.getAttractions()).thenReturn(attractions);
		mockMvc.perform(get(root + "/attractions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].city", is("city")));
			
	}
	
	@Test
	void getUserLocationReturnsLocationWhenOk() throws Exception {
		when(gpsService.getUserLocation(anyString())).thenReturn(visitedLocation);
		mockMvc.perform(get(root + "/location/" + userName))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}
	
	@Test
	void getUserLocationThrowsExceptionWhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(gpsService).getUserLocation(anyString());
		mockMvc.perform(get(root + "/location/" + userName))
			.andExpect(status().is(404));
	}
	
	@Test
	void getUserLocationThrowsExceptionWhenArgInvalid() throws Exception {
		mockMvc.perform(get(root + "/location/ "))
			.andExpect(status().is(400));
	}
	
	@Test
	void getAllUsersLastLocationReturnsMapWhenOk() throws Exception {
		Map<UUID, Location> res = new HashMap<>();
		res.put(userId, location);
		when(gpsService.getAllUsersLastLocation()).thenReturn(res);
		mockMvc.perform(get(root + "/lastlocations"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$." + userId.toString() + ".latitude", is(10d)));
	}
	
	@Test
	void getUserVisitedLocationsReturnsListWhenOk() throws UserNotFoundException, Exception {
		when(gpsService.getUserVisitedLocations(any(UUID.class))).thenReturn(visitedLocations);
		mockMvc.perform(get(root + "/locations/" + userId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].location.longitude", is(20d)));
	}
	
	@Test
	void getUserVisitedLocationsThrowsExceptionWhenUserNotFound() throws UserNotFoundException, Exception {
		doThrow(UserNotFoundException.class).when(gpsService).getUserVisitedLocations(any(UUID.class));
		mockMvc.perform(get(root + "/locations/" + userId))
			.andExpect(status().is(404));
	}
	
	@Test
	void getUserVisitedLocationsThrowsExceptionWhenArgInvalid() throws Exception {
		mockMvc.perform(get(root + "/locations/ "))
			.andExpect(status().is(400));
	}
	
	@Test
	void getNearByAttractionsReturnsListWhenOk() throws Exception {
		when(gpsService.getNearByAttractions(anyString())).thenReturn(attractions);
		mockMvc.perform(get(root + "/nearbyattractions/" + userName))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].city", is("city")));
		
	}
	
	@Test
	void getNearByAttractionsThrowsExceptionWhenUserNotFound() throws Exception {
		doThrow(UserNotFoundException.class).when(gpsService).getNearByAttractions(anyString());
		mockMvc.perform(get(root + "/nearbyattractions/" + userName))
			.andExpect(status().is(404));
	}
	
	@Test
	void getNearByAttractionsThrowsExceptionWhenArgInvalid() throws Exception {
		mockMvc.perform(get(root + "/nearbyattractions/ "))
			.andExpect(status().is(400));
	}

}
