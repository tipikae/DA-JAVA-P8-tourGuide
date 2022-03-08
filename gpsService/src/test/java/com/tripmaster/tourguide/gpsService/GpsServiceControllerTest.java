package com.tripmaster.tourguide.gpsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.gpsService.controller.GpsServiceController;
import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;
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

	@Test
	void getAttractionsReturnsJsonListWhenOk() throws Exception {
		Attraction attraction = new Attraction("Disneyland", "Anaheim", "CA", 33.817595, -117.922008);
		List<Attraction> attractions = new ArrayList<>();
		attractions.add(attraction);
		when(gpsService.getAttractions()).thenReturn(attractions);
		mockMvc.perform(get("/gpsservice/attractions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{longitude: -117.922008,"
						+ "latitude: 33.817595,"
						+ "attractionName: Disneyland,"
						+ "city: Anaheim,"
						+ "state\": CA,"
						+ "attractionId: 3c8ec256-eed2-406b-9ed4-2d744a5e1c45}]"))
			.andExpect(status().isOk());
			
	}
	
	@Test
	void getUserLocationReturnsLocationWhenOk() throws Exception {
		UUID userId = UUID.fromString("019b04a9-067a-4c76-8817-ee75088c3822");
		Location location = new Location(-110.464573, -25.075681);
		Date now = new Date();
		VisitedLocation visitedLocation = new VisitedLocation(userId, location, now);
		when(gpsService.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		mockMvc.perform(get("/gpsservice/userlocation?userId=" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "userId: 019b04a9-067a-4c76-8817-ee75088c3822,"
						+ "location: {"
						+ "longitude: -110.464573,"
						+ "latitude: -25.075681"
						+ "},"
						+ "timeVisited: " + now.toString()
						+ "}"))
			.andExpect(status().isOk());
	}
	
	@Test
	void getUserLocationReturns400WhenCustomNumberFormatException() 
			throws Exception {
		UUID userId = UUID.randomUUID();
		doThrow(CustomNumberFormatException.class).when(gpsService).getUserLocation(any(UUID.class));
		mockMvc.perform(get("/gpsservice/userlocation?userId=" + userId)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void getUserLocationReturns404WhenUserIdIsInvalid() 
			throws Exception {
		doThrow(CustomNumberFormatException.class).when(gpsService).getUserLocation(any(UUID.class));
		mockMvc.perform(get("/gpsservice/userlocation?userId=")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

}
