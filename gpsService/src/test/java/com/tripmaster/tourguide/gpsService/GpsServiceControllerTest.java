package com.tripmaster.tourguide.gpsService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
		mockMvc.perform(get("/gpsservice/attractions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].city", is("Anaheim")));
			
	}
	
	@Test
	void getUserLocationReturnsLocationWhenOk() throws Exception {
		UUID userId = UUID.fromString("019b04a9-067a-4c76-8817-ee75088c3822");
		Location location = new Location(-110.464573, -25.075681);
		Date now = new Date();
		VisitedLocation visitedLocation = new VisitedLocation(userId, location, now);
		when(gpsService.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		mockMvc.perform(get("/gpsservice/userlocation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", userId.toString()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(userId.toString())));
	}

}
