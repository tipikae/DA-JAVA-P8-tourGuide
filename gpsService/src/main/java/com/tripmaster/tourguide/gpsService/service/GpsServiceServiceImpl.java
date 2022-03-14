/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.gpsService.exceptions.CustomNumberFormatException;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

/**
 * GpsService service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class GpsServiceServiceImpl implements IGpsServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GpsServiceServiceImpl.class);
	
	@Autowired
	private IVisitedLocationRepository visitedLocationRepository;
	
	@Autowired
	private GpsUtil gpsUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Attraction> getAttractions() {
		LOGGER.debug("getAttractions");
		return gpsUtil.getAttractions();
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<UUID, Location> getAllUsersLastLocation() throws CustomNumberFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VisitedLocation> getUserVisitedLocations(UUID userId) throws CustomNumberFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocation getUserLastVisitedLocation(UUID userId) throws CustomNumberFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocation addUserVisitedLocation(VisitedLocation visitedLocation) throws CustomNumberFormatException {
		// TODO Auto-generated method stub
		return null;
	}

}
