package com.tripmaster.tourguide.gpsService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.TrackLocationException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.remoteServices.IUserService;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

@SpringBootTest
class GpsServiceIT {
	
	@Autowired
	private IGpsServiceService gpsService;
	
	@Autowired
	private IUserService userService;
	
	private final String username = "jon";

	@Test
	void getAttractions() throws ConverterDTOException, ConverterLibException {
		assertTrue(gpsService.getAttractions().size() > 0);
	}

	@Test
	void getUserLocation() 
			throws HttpException, UserNotFoundException, ConverterDTOException, ConverterLibException, 
			TrackLocationException {
		UUID userId = userService.getUserId(username);
		assertEquals(userId, gpsService.getUserLocation(username).getUserId());
	}

	@Test
	void getAllUsersLastLocation() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException, 
			TrackLocationException {
		gpsService.getUserLocation(username);
		assertTrue(gpsService.getAllUsersLastLocation().size() > 0);
	}

	@Test
	void getUserVisitedLocations() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException, 
			TrackLocationException {
		UUID userId = userService.getUserId(username);
		gpsService.getUserLocation(username);
		assertEquals(1, gpsService.getUserVisitedLocations(userId).size());
	}

	@Test
	void getNearByAttractions() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException, 
			TrackLocationException {
		gpsService.getUserLocation(username);
		assertEquals(5, gpsService.getNearByAttractions(username).size());
	}

	@Test
	void trackUserLocation() 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException, 
			TrackLocationException {
		UUID userId = userService.getUserId(username);
		assertEquals(userId, gpsService.trackUserLocation(userId).join().getUserId());
	}

}