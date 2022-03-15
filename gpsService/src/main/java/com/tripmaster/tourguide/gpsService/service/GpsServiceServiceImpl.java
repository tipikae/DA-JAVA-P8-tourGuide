/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripmaster.tourguide.gpsService.dto.NewVisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
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
	public VisitedLocation getUserLocation(UUID userId) {
		LOGGER.debug("getUserLocation: userId=" + userId);
		return gpsUtil.getUserLocation(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<UUID, Location> getAllUsersLastLocation() {
		LOGGER.debug("getAllUsersLastLocation");
		
		Map<UUID, Location> allUsersLastLocation = new HashMap<>();
		for(Entry<UUID, List<VisitedLocation>> entry: visitedLocationRepository.findAll().entrySet()) {
			UUID userId = entry.getKey();
			List<VisitedLocation> visitedLocations = entry.getValue();
			Location location = visitedLocations.get(visitedLocations.size() - 1).location;
			allUsersLastLocation.put(userId, location);
		}
		
		return allUsersLastLocation;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public List<VisitedLocation> getUserVisitedLocations(UUID userId) throws UserNotFoundException {
		LOGGER.debug("getUserVisitedLocations: userId=" + userId);
		
		Optional<List<VisitedLocation>> optional = visitedLocationRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserVisitedLocations: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("User with userId=" + userId + " not found.");
		}
		
		return optional.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocation getUserLastVisitedLocation(UUID userId) throws UserNotFoundException {
		LOGGER.debug("getUserLastVisitedLocation: userId=" + userId);
		
		Optional<List<VisitedLocation>> optional = visitedLocationRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserVisitedLocations: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("User with userId=" + userId + " not found.");
		}
		
		List<VisitedLocation> visitedLocations = optional.get();
		VisitedLocation last = visitedLocations.get(visitedLocations.size() - 1);
		
		return last;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VisitedLocation addUserVisitedLocation(NewVisitedLocationDTO newVisitedLocationDTO) {
		LOGGER.debug("addUserVisitedLocation: userId=" + newVisitedLocationDTO.getUserId()
				+ ", latitude=" + newVisitedLocationDTO.getLocation().getLatitude()
				+ ", longitude=" + newVisitedLocationDTO.getLocation().getLongitude());
		
		UUID userId = newVisitedLocationDTO.getUserId();
		Location location = new Location(newVisitedLocationDTO.getLocation().getLatitude(),
				newVisitedLocationDTO.getLocation().getLongitude());
		Date timeVisited = newVisitedLocationDTO.getTimeVisited();
		VisitedLocation visitedLocation = new VisitedLocation(userId, location, timeVisited);
		visitedLocationRepository.save(visitedLocation);
		
		return visitedLocation;
	}

}
