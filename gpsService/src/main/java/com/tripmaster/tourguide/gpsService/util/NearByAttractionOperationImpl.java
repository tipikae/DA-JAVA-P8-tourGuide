/**
 * 
 */
package com.tripmaster.tourguide.gpsService.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.clients.IRewardServiceClient;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.model.MAttraction;
import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.model.NearByAttraction;

import gpsUtil.GpsUtil;

/**
 * NearByAttraction operations.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class NearByAttractionOperationImpl implements INearByAttractionOperation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NearByAttractionOperationImpl.class);
	private static final int MAX = 5;
	
	@Autowired
	private GpsUtil gpsUtil;
	
	@Autowired
	private IConverterLibAttraction attractionConverter;
	
	@Autowired
	private IConverterLibLocation locationConverter;
	
	@Autowired
	private IHelper helper;
	
	@Autowired
	private IRewardServiceClient rewardService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NearByAttraction> getNearByAttractions(UUID userId) 
			throws ConverterLibException, HttpException {
		LOGGER.debug("getNearByAttractions: userId=" + userId);
		
		List<NearByAttraction> nearByAttractions = null;
		List<UUID> attractionIds = null;
		int max = 0;
		
		// find
		List<MAttraction> mAttractions = findAttractions();
		MLocation userLocation = findUserLocation(userId);
		
		// load
		Map<NearByAttraction, UUID> map = loadMap(mAttractions, userLocation);
		
		// sublist
		nearByAttractions = new ArrayList<>(map.keySet());
		attractionIds = new ArrayList<>(map.values());
		max = (nearByAttractions.size() < MAX ? nearByAttractions.size() : MAX);
		nearByAttractions = nearByAttractions.subList(0, max);
		
		// points
		for(NearByAttraction nearByAttraction: nearByAttractions) {
			int index = nearByAttractions.indexOf(nearByAttraction);
			UUID attractionId = attractionIds.get(index);
			int points = rewardService.getAttractionRewardPoints(attractionId, userId);
			nearByAttraction.setPoints(points);
		}
		
		return nearByAttractions;
	}
	
	private List<MAttraction> findAttractions() throws ConverterLibException {
		return attractionConverter.convertLibAttractionsToMAttractions(gpsUtil.getAttractions());
	}
	
	private MLocation findUserLocation(UUID userId) throws ConverterLibException {
		return locationConverter.convertLibModelToModel(gpsUtil.getUserLocation(userId).location);
	}
	
	private Map<NearByAttraction, UUID> loadMap(List<MAttraction> attractions, MLocation userLocation) {
		Map<NearByAttraction, UUID> map = new TreeMap<>(
				new Comparator<NearByAttraction>() {

			@Override
			public int compare(NearByAttraction attraction1, NearByAttraction attraction2) {
				Double distance1 = Double.valueOf(attraction1.getDistance());
				Double distance2 = Double.valueOf(attraction2.getDistance());
				return distance1.compareTo(distance2);
			}
		});
		
		attractions.stream().forEach(attraction -> {
			MLocation attractionLocation = new MLocation(attraction.getLatitude(), 
					attraction.getLongitude());
			double distance = helper.calculateDistance(attraction.getLatitude(), 
					attraction.getLongitude(), userLocation.getLatitude(), userLocation.getLongitude());
			String attractionName = attraction.getAttractionName();
			NearByAttraction nearByAttraction = new NearByAttraction(attractionName, attractionLocation,
					userLocation, distance, 0);
			map.put(nearByAttraction, attraction.getAttractionId());
		});
		
		return map;
	}

}
