package com.tripmaster.tourguide.tripPricerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.tripmaster.tourguide.tripPricerService.service.TripPricerServiceServiceImpl;

import tripPricer.Provider;
import tripPricer.TripPricer;

@ExtendWith(SpringExtension.class)
class TripPricerServiceServiceTest {
	
	@Mock
	private TripPricer tripPricer;
	
	@InjectMocks
	private TripPricerServiceServiceImpl tripPricerService;
	
	@BeforeAll
	private static void setUp() {
		ReflectionTestUtils.setField(TripPricerServiceServiceImpl.class, "apiKey", "test-api-key");
	}

	@Test
	void getPriceReturnsListWhenOk() {
		Provider provider = new Provider(UUID.randomUUID(), "provider1", 100.0);
		List<Provider> providers = new ArrayList<>();
		providers.add(provider);
		when(tripPricer.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt()))
			.thenReturn(providers);
		assertEquals(1, tripPricerService.getPrice(UUID.randomUUID(), 2, 2, 7, 100).size());
	}

	@Test
	void getProviderNameReturnsNameWhenOk() {
		String providerName = "provider1";
		when(tripPricer.getProviderName(anyString(), anyInt())).thenReturn(providerName);
		assertEquals(providerName, tripPricerService.getProviderName(0));
	}

}
