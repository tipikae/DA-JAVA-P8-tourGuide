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
import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

@WebMvcTest(controllers = GpsServiceController.class)
class GpsServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IGpsServiceService gpsService;
	
	private static String root;
	private static String userName;
	private static UUID userId;
	private static LocationDTO locationDTO;
	private static VisitedLocationDTO visitedLocationDTO;
	private static List<VisitedLocationDTO> visitedLocationDTOs;
	private static AttractionDTO attractionDTO;
	private static List<AttractionDTO> attractionDTOs;
	
	@BeforeAll
	private static void setUp() {
		root = "/gpsservice";
		userName = "username";
		userId = UUID.randomUUID();
		locationDTO = new LocationDTO();
		locationDTO.setLatitude(10d);
		locationDTO.setLongitude(20d);
		visitedLocationDTO = new VisitedLocationDTO();
		visitedLocationDTO.setUserId(userId);
		visitedLocationDTO.setLocation(locationDTO);
		visitedLocationDTOs = new ArrayList<>();
		visitedLocationDTOs.add(visitedLocationDTO);
		attractionDTO = new AttractionDTO();
		attractionDTO.setCity("city");
		attractionDTOs = new ArrayList<>();
		attractionDTOs.add(attractionDTO);
	}

	@Test
	void getAttractionsReturnsJsonListWhenOk() throws Exception {
		when(gpsService.getAttractions()).thenReturn(attractionDTOs);
		mockMvc.perform(get(root + "/attractions"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].city", is("city")));
			
	}
	
	@Test
	void getUserLocationReturnsLocationWhenOk() throws Exception {
		when(gpsService.getUserLocation(anyString())).thenReturn(locationDTO);
		mockMvc.perform(get(root + "/location/" + userName))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.latitude", is(locationDTO.getLatitude())));
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
		Map<UUID, LocationDTO> res = new HashMap<>();
		res.put(userId, locationDTO);
		when(gpsService.getAllUsersLastLocation()).thenReturn(res);
		mockMvc.perform(get(root + "/lastlocations"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$." + userId.toString() + ".latitude", is(10d)));
	}
	
	@Test
	void getUserVisitedLocationsReturnsListWhenOk() throws UserNotFoundException, Exception {
		when(gpsService.getUserVisitedLocations(any(UUID.class))).thenReturn(visitedLocationDTOs);
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
		NearByAttractionDTO nearByAttractionDTO = new NearByAttractionDTO();
		nearByAttractionDTO.setAttractionName("attractionName");
		List<NearByAttractionDTO> nearByAttractionDTOs = new ArrayList<>();
		nearByAttractionDTOs.add(nearByAttractionDTO);
		when(gpsService.getNearByAttractions(anyString())).thenReturn(nearByAttractionDTOs);
		mockMvc.perform(get(root + "/nearbyattractions/" + userName))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].attractionName", is("attractionName")));
		
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
