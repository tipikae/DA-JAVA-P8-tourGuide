package com.tripmaster.tourguide.gpsService.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tripmaster.tourguide.gpsService.converters.IConverterLibAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;
import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.remoteServices.IRewardService;
import com.tripmaster.tourguide.gpsService.util.IHelper;
import com.tripmaster.tourguide.gpsService.util.INearByAttractionOperation;
import com.tripmaster.tourguide.gpsService.util.NearByAttractionOperationImpl;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
class NearByAttractionOperationTest {
	
	@Mock
	private GpsUtil gpsUtil;
	
	@Mock
	private IHelper helper;
	
	@Mock
	private IConverterLibAttraction attractionConverter;
	
	@Mock
	private IConverterLibLocation locationConverter;
	
	@Mock
	private IRewardService rewardService;
	
	@InjectMocks
	private INearByAttractionOperation nearByAttractionOperation = new NearByAttractionOperationImpl();
	
	private static List<Attraction> attractions;
	private static List<MAttraction> mAttractions;
	private static Location location;
	private static MLocation mLocation;
	private static VisitedLocation visitedLocation;
	
	@BeforeAll
	private static void setUp() {
		attractions = new ArrayList<>();
		mAttractions = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Attraction attraction = new Attraction("name" + i, "city", "state", 10d, 20d);
			attractions.add(attraction);
			MAttraction mAttraction = new MAttraction("name" + i, "city", "state", 10d, 20d);
			mAttraction.setAttractionId(UUID.randomUUID());
			mAttractions.add(mAttraction);
		}
		location = new Location(10d, 20d);
		mLocation = new MLocation(10d, 20d);
		visitedLocation = new VisitedLocation(null, location, null);
	}

	@Test
	void getNearByAttractionsReturnsListWhenOk() throws ConverterLibException, HttpException {
		when(gpsUtil.getAttractions()).thenReturn(attractions);
		when(attractionConverter.convertLibAttractionsToMAttractions(anyList())).thenReturn(mAttractions);
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		when(locationConverter.convertLibModelToModel(any(Location.class))).thenReturn(mLocation);
		when(helper.calculateDistance(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
			.thenReturn(10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d, 90d, 100d);
		when(rewardService.getAttractionRewardPoints(any(UUID.class), any(UUID.class))).thenReturn(100);
		assertEquals(5, nearByAttractionOperation.getNearByAttractions(UUID.randomUUID()).size());
	}

	@Test
	void getNearByAttractionsThrowsExceptionWhenServiceError() throws ConverterLibException, HttpException {
		when(gpsUtil.getAttractions()).thenReturn(attractions);
		when(attractionConverter.convertLibAttractionsToMAttractions(anyList())).thenReturn(mAttractions);
		when(gpsUtil.getUserLocation(any(UUID.class))).thenReturn(visitedLocation);
		when(locationConverter.convertLibModelToModel(any(Location.class))).thenReturn(mLocation);
		when(helper.calculateDistance(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
			.thenReturn(10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d, 90d, 100d);
		doThrow(HttpException.class).when(rewardService)
			.getAttractionRewardPoints(any(UUID.class), any(UUID.class));
		assertThrows(HttpException.class, 
				() -> nearByAttractionOperation.getNearByAttractions(UUID.randomUUID()));
	}

}
