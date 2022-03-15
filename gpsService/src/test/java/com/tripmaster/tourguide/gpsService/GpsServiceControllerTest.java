package com.tripmaster.tourguide.gpsService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.gpsService.controller.GpsServiceController;
import com.tripmaster.tourguide.gpsService.dto.NewVisitedLocationDTO;
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
	
	private static UUID userId;
	private static Location location;
	private static VisitedLocation visitedLocation;
	private static List<VisitedLocation> visitedLocations;
	private static Map<UUID, List<VisitedLocation>> map;
	
	@BeforeAll
	private static void setUp() {
		userId = UUID.randomUUID();
		location = new Location(10d, 20d);
		visitedLocation = new VisitedLocation(userId, location, new Date());
		visitedLocations = new ArrayList<>();
		visitedLocations.add(visitedLocation);
		map = new HashMap<>();
		map.put(userId, visitedLocations);
	}

	@Test
	void getAttractionsReturnsJsonListWhenOk() throws Exception {
		Attraction attraction = new Attraction("Disneyland", "Anaheim", "CA", 33.817595, -117.922008);
		List<Attraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		when(gpsService.getAttractions()).thenReturn(attractions);
		mockMvc.perform(get("/gpsservice/attractions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].city", is("Anaheim")));
			
	}
	
	@Test
	void getUserLocationReturnsLocationWhenOk() throws Exception {
		when(gpsService.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		mockMvc.perform(get("/gpsservice/location")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}
	
	@Test
	void getAllUsersLastLocationReturnsMapWhenOk() throws Exception {
		Map<UUID, Location> res = new HashMap<>();
		res.put(userId, location);
		when(gpsService.getAllUsersLastLocation()).thenReturn(res);
		mockMvc.perform(get("/gpsservice/lastlocations"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$." + userId.toString() + ".latitude", is(10d)));
	}
	
	@Test
	void getUserVisitedLocationsReturnsListWhenOk() throws UserNotFoundException, Exception {
		when(gpsService.getUserVisitedLocations(any(UUID.class))).thenReturn(visitedLocations);
		mockMvc.perform(get("/gpsservice/locations")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].location.longitude", is(20d)));
	}
	
	@Test
	void getUserLastVisitedLocationReturnsVisitedLocationWhenOk() throws UserNotFoundException, Exception {
		when(gpsService.getUserLastVisitedLocation(any(UUID.class))).thenReturn(visitedLocation);
		mockMvc.perform(get("/gpsservice/lastlocation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}
	
	@Test
	void addUserVisitedLocationReturnsVisitedLocationWhenOk() throws Exception {
		when(gpsService.addUserVisitedLocation(any(NewVisitedLocationDTO.class))).thenReturn(visitedLocation);
		mockMvc.perform(post("/gpsservice/location")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":\"776aae36-5afb-44e8-a357-c4f20242eb41\","
						+ "\"location\":{\"longitude\":20.0,\"latitude\":10.0},"
						+ "\"timeVisited\":\"2022-03-14T18:01:01.846+00:00\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}

}
