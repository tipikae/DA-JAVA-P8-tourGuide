package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;
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

import com.tripmaster.tourguide.gpsService.controller.GpsUtilController;
import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;
import com.tripmaster.tourguide.gpsService.service.IGpsUtilService;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@WebMvcTest(controllers = GpsUtilController.class)
class GpsUtilControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IGpsUtilService gpsService;

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
		Location location = new Location(10d, 20d);
		VisitedLocation visitedLocation = new VisitedLocation(UUID.fromString("test"), location, new Date());
		when(gpsService.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		mockMvc.perform(get("/gpsservice/userlocation?userId=" + UUID.fromString("test"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
			.andExpect(status().isOk());
	}
	
	@Test
	void getUserLocationReturnsErrorLocationWhenCustomNumberFormatException() 
			throws Exception {
		doThrow(CustomNumberFormatException.class).when(gpsService).getUserLocation(any(UUID.class));
		mockMvc.perform(get("/gpsservice/userlocation?userId=" + UUID.fromString("test"))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is4xxClientError());
	}

}
