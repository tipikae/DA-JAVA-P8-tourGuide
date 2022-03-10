package com.tripmaster.tourguide.tripPricerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tripmaster.tourguide.tripPricerService.controller.TripPricerServiceController;
import com.tripmaster.tourguide.tripPricerService.service.ITripPricerServiceService;

import tripPricer.Provider;

@WebMvcTest(TripPricerServiceController.class)
class TripPricerServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ITripPricerServiceService tripPricerService;

	@Test
	void getPriceReturnsListWhenOk() throws Exception {
		UUID tripId = UUID.randomUUID();
		Provider provider = new Provider(tripId, "provider1", 100.0);
		List<Provider> providers = new ArrayList<>();
		providers.add(provider);
		when(tripPricerService.getPrice(any(), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		mockMvc.perform(get("/trippricer/price")
				.contentType(MediaType.APPLICATION_JSON)
				.param("userId", UUID.randomUUID().toString())
				.param("nbAdults", "1")
				.param("nbChildren", "1")
				.param("tripDuration", "1")
				.param("rewardPoints", "1"))
			.andExpect(status().isOk())
			.andExpect(content().json(
					"[{\"name\":\"provider1\","
					+ "\"price\":100.0,"
					+ "\"tripId\":\"" + tripId.toString() + "\"}]"));
	}

	@Test
	void getProviderNameReturnsNameWhenOk() throws Exception {
		String providerName = "provider1";
		when(tripPricerService.getProviderName(anyInt())).thenReturn(providerName);
		mockMvc.perform(get("/trippricer/provider")
				.contentType(MediaType.APPLICATION_JSON)
				.param("adults", "1"))
			.andExpect(status().isOk())
			.andExpect(content().string(providerName));
	}

}
