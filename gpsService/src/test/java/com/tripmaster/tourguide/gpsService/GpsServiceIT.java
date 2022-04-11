package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.clients.IUserServiceClient;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.service.GpsServiceServiceImpl;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

@SpringBootTest
class GpsServiceIT {
	
	@Autowired
	private IGpsServiceService gpsService;
	
	@Autowired
	private IUserServiceClient userService;
	
	private final String username = "jon";
	
	@BeforeAll
	private static void setUp() {
		if(GpsServiceServiceImpl.executorService.isTerminated()) {
			GpsServiceServiceImpl.executorService = Executors.newFixedThreadPool(1000);
		}
	}

	@Test
	void getAttractions() throws ConverterDTOException, ConverterLibException {
		assertTrue(gpsService.getAttractions().size() > 0);
	}

	@Test
	void getUserLocation() 
			throws HttpException, UserNotFoundException, ConverterDTOException, ConverterLibException {
		UUID userId = userService.getUserId(username);
		assertEquals(userId, gpsService.getUserLocation(username).getUserId());
	}

	@Test
	void getAllUsersLastLocation() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		gpsService.getUserLocation(username);
		assertTrue(gpsService.getAllUsersLastLocation().size() > 0);
	}

	@Test
	void getUserVisitedLocations() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		UUID userId = userService.getUserId(username);
		gpsService.getUserLocation(username);
		assertEquals(1, gpsService.getUserVisitedLocations(userId).size());
	}

	@Test
	void getNearByAttractions() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		gpsService.getUserLocation(username);
		assertEquals(5, gpsService.getNearByAttractions(username).size());
	}

	@Test
	void trackUserLocation() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		UUID userId = userService.getUserId(username);
		assertEquals(userId, gpsService.trackUserLocation(userId).join().getUserId());
	}

}
