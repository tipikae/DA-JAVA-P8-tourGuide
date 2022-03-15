/**
 * 
 */
package com.tripmaster.tourguide.gpsService.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import gpsUtil.location.VisitedLocation;

/**
 * VisitedLocation repository implementation.
 * @author tipikae
 *  @version 1.0
 *
 */
@Repository
public class VisitedLocationRepositoryImpl implements IVisitedLocationRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VisitedLocationRepositoryImpl.class);
	
	private static Map<UUID, List<VisitedLocation>> internalVisitedLocations = new HashMap<>();

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public VisitedLocation save(VisitedLocation visitedLocation) {
		LOGGER.debug("save: userId=" + visitedLocation.userId 
				+ ", latitude=" + visitedLocation.location.latitude
				+ ", longitude=" + visitedLocation.location.longitude
				+ ", timeVisited=" + visitedLocation.timeVisited.toString());
		
		if(internalVisitedLocations.containsKey(visitedLocation.userId)) {
			List<VisitedLocation> visitedLocations = internalVisitedLocations.get(visitedLocation.userId);
			visitedLocations.add(visitedLocation);
			internalVisitedLocations.replace(visitedLocation.userId, visitedLocations);
		} else {
			List<VisitedLocation> visitedLocations = new ArrayList<>();
			visitedLocations.add(visitedLocation);
			internalVisitedLocations.put(visitedLocation.userId, visitedLocations);
		}
		
		return visitedLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<VisitedLocation>> findByUserId(UUID userId) {
		LOGGER.debug("findByUserId: userId=" + userId);
		
		if(internalVisitedLocations.containsKey(userId)) {
			return Optional.of(internalVisitedLocations.get(userId));
		}
		
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<UUID, List<VisitedLocation>> findAll() {
		LOGGER.debug("findAll");
		return internalVisitedLocations;
	}

}
