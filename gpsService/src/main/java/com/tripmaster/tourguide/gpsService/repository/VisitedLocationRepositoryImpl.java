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

import com.tripmaster.tourguide.gpsService.exceptions.CustomClassCastException;
import com.tripmaster.tourguide.gpsService.exceptions.CustomIllegalArgumentException;
import com.tripmaster.tourguide.gpsService.exceptions.FindByUserIdOperationException;
import com.tripmaster.tourguide.gpsService.exceptions.SaveOperationException;

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
	public VisitedLocation save(VisitedLocation visitedLocation) 
			throws CustomClassCastException, CustomIllegalArgumentException, SaveOperationException {
		LOGGER.debug("save: userId=" + visitedLocation.userId 
				+ ", latitude=" + visitedLocation.location.latitude
				+ ", longitude=" + visitedLocation.location.longitude
				+ ", timeVisited=" + visitedLocation.timeVisited.toString());
		
		try {
			if(internalVisitedLocations.containsKey(visitedLocation.userId)) {
				List<VisitedLocation> visitedLocations = internalVisitedLocations.get(visitedLocation.userId);
				visitedLocations.add(visitedLocation);
				internalVisitedLocations.replace(visitedLocation.userId, visitedLocations);
			} else {
				List<VisitedLocation> visitedLocations = new ArrayList<>();
				visitedLocations.add(visitedLocation);
				internalVisitedLocations.put(visitedLocation.userId, visitedLocations);
			}
		} catch (ClassCastException e) {
			LOGGER.debug("save: error: ClassCastException: " + e.getMessage());
			throw new CustomClassCastException(e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.debug("save: error: IllegalArgumentException: " + e.getMessage());
			throw new CustomIllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("save: error: " + e.getMessage());
			throw new SaveOperationException(e.getMessage());
		}
		
		return visitedLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<List<VisitedLocation>> findByUserId(UUID userId) 
			throws CustomClassCastException, FindByUserIdOperationException {
		LOGGER.debug("findByUserId: userId=" + userId);
		
		try {
			if(internalVisitedLocations.containsKey(userId)) {
				return Optional.of(internalVisitedLocations.get(userId));
			} else {
				return Optional.empty();
			}
		} catch (ClassCastException e) {
			LOGGER.debug("findByUserId: error: ClassCastException: " + e.getMessage());
			throw new CustomClassCastException(e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("findByUserId: error: " + e.getMessage());
			throw new FindByUserIdOperationException(e.getMessage());
		}
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
