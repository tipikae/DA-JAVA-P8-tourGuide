/**
 * 
 */
package com.tripmaster.tourguide.gpsService.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;

/**
 * VisitedLocation repository implementation.
 * @author tipikae
 *  @version 1.0
 *
 */
@Repository
public class VisitedLocationRepositoryImpl implements IVisitedLocationRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VisitedLocationRepositoryImpl.class);
	
	private static Map<UUID, List<MVisitedLocation>> internalVisitedLocations 
		= new ConcurrentHashMap<>();

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public MVisitedLocation save(MVisitedLocation visitedLocation) {
		LOGGER.debug("save: userId=" + visitedLocation.getUserId() 
				+ ", latitude=" + visitedLocation.getLocation().getLatitude()
				+ ", longitude=" + visitedLocation.getLocation().getLongitude()
				+ ", timeVisited=" + visitedLocation.getTimeVisited().toString());
		
		if(internalVisitedLocations.containsKey(visitedLocation.getUserId())) {
			List<MVisitedLocation> visitedLocations = 
					internalVisitedLocations.get(visitedLocation.getUserId());
			visitedLocations.add(visitedLocation);
			internalVisitedLocations.replace(visitedLocation.getUserId(), visitedLocations);
		} else {
			List<MVisitedLocation> visitedLocations = new CopyOnWriteArrayList<>();
			visitedLocations.add(visitedLocation);
			internalVisitedLocations.put(visitedLocation.getUserId(), visitedLocations);
		}
		
		return visitedLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<MVisitedLocation>> findByUserId(UUID userId) {
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
	public Map<UUID, List<MVisitedLocation>> findAll() {
		LOGGER.debug("findAll");
		return internalVisitedLocations;
	}

}
