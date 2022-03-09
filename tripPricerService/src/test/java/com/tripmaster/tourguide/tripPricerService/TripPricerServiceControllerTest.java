package com.tripmaster.tourguide.tripPricerService;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		Provider provider = new Provider(UUID.randomUUID(), "provider1", 100.0);
		List<Provider> providers = new ArrayList<>();
		providers.add(provider);
		when(tripPricerService.getPrice(any(), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		mockMvc.perform(get("/trippricer/price")
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
			.andExpect(status().isOk());
	}

	@Test
	void getProviderNameReturnsNameWhenOk() throws Exception {
		String providerName = "provider1";
		when(tripPricerService.getProviderName(anyInt())).thenReturn(providerName);
		mockMvc.perform(get("/trippricer/provider")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" + providerName + "}"))
			.andExpect(status().isOk());
	}

}
