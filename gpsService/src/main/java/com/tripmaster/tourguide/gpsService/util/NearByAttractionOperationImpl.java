/**
 * 
 */
package com.tripmaster.tourguide.gpsService.util;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.converters.IConverterLibAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibLocation;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NearByAttraction> getNearByAttractions(UUID userId) throws ConverterLibException {
		LOGGER.debug("getNearByAttractions: userId=" + userId);
		
		List<NearByAttraction> nearByAttractions = new ArrayList<>();
		SortedSet<E>
		
		//find
		List<MAttraction> mAttractions = findAttractions();
		MLocation userLocation = findUserLocation(userId);
		
		//sort
		
		
		return nearByAttractions;
	}
	
	private List<MAttraction> findAttractions() throws ConverterLibException {
		return attractionConverter.convertLibAttractionsToMAttractions(gpsUtil.getAttractions());
	}
	
	private MLocation findUserLocation(UUID userId) throws ConverterLibException {
		return locationConverter.convertLibModelToModel(gpsUtil.getUserLocation(userId).location);
	}

}
