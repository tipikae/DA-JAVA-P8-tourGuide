/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

/**
 * Implementation of IGpsUtilService for accessing gpsUtil lib.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class GpsServiceServiceImpl implements IGpsServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GpsServiceServiceImpl.class);
	
	@Autowired
	private GpsUtil gpsUtil;

	@Override
	public List<Attraction> getAttractions() {
		LOGGER.debug("getAttractions");
		return gpsUtil.getAttractions();
	}

	@Override
	public VisitedLocation getUserLocation(UUID userId) throws CustomNumberFormatException {
		LOGGER.debug("getUserLocation: userId=" + userId);
		try {
			return gpsUtil.getUserLocation(userId);
		} catch (NumberFormatException e) {
			LOGGER.debug("getUserLocation: error=" + e.getMessage());
			throw new CustomNumberFormatException("Bad number format.");
		}
	}

}
