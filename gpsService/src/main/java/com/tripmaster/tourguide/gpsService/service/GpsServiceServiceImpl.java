/**
 * 
 */
package com.tripmaster.tourguide.gpsService.service;

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

import com.tripmaster.tourguide.gpsService.converters.IConverterDTOAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTOLocation;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTONearByAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterDTOVisitedLocation;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibAttraction;
import com.tripmaster.tourguide.gpsService.converters.IConverterLibVisitedLocation;
import com.tripmaster.tourguide.gpsService.dto.AttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.LocationDTO;
import com.tripmaster.tourguide.gpsService.dto.NearByAttractionDTO;
import com.tripmaster.tourguide.gpsService.dto.VisitedLocationDTO;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.exceptions.UserNotFoundException;
import com.tripmaster.tourguide.gpsService.model.MLocation;
import com.tripmaster.tourguide.gpsService.model.MVisitedLocation;
import com.tripmaster.tourguide.gpsService.remoteServices.IRewardService;
import com.tripmaster.tourguide.gpsService.remoteServices.IUserService;
import com.tripmaster.tourguide.gpsService.repository.IVisitedLocationRepository;
import com.tripmaster.tourguide.gpsService.util.INearByAttractionOperation;

import gpsUtil.GpsUtil;

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
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRewardService rewardService;
	
	@Autowired
	private IConverterDTOAttraction attractionDTOConverter;
	
	@Autowired
	private IConverterLibAttraction attractionLibConverter;
	
	@Autowired
	private IConverterDTOLocation locationDTOConverter;
	
	@Autowired
	private IConverterDTOVisitedLocation visitedLocationDTOConverter;
	
	@Autowired
	private IConverterLibVisitedLocation visitedLocationLibConverter;
	
	@Autowired
	private INearByAttractionOperation nearByAttractionOperation;
	
	@Autowired
	private IConverterDTONearByAttraction nearByAttractionDTOConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AttractionDTO> getAttractions() throws ConverterDTOException, ConverterLibException {
		LOGGER.debug("getAttractions");
		
		List<AttractionDTO> attractionDTOs = attractionDTOConverter.convertAttractionsToDTos(
				attractionLibConverter.convertLibAttractionsToMAttractions(gpsUtil.getAttractions()));
		LOGGER.debug("getAttractions: returns " + attractionDTOs.size() + " items.");
		
		return attractionDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocationDTO getUserLocation(String userName) 
			throws HttpException, ConverterDTOException, ConverterLibException {
		LOGGER.debug("getUserLocation: userName=" + userName);
		
		UUID userId = userService.getUserId(userName);
		Optional<List<MVisitedLocation>> optional = visitedLocationRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			// trackUserLocation
			return locationDTOConverter.convertEntityToDTO(trackUserLocation(userId).getLocation());
		}
		
		List<MVisitedLocation> mVisitedLocations = optional.get();
		int last = mVisitedLocations.size() - 1;
		MVisitedLocation mVisitedLocation = mVisitedLocations.get(last);
		
		return locationDTOConverter.convertEntityToDTO(mVisitedLocation.getLocation());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<UUID, LocationDTO> getAllUsersLastLocation() throws ConverterDTOException {
		LOGGER.debug("getAllUsersLastLocation");
		
		Map<UUID, LocationDTO> allUsersLastLocation = new HashMap<>();
		for(Entry<UUID, List<MVisitedLocation>> entry: visitedLocationRepository.findAll().entrySet()) {
			UUID userId = entry.getKey();
			List<MVisitedLocation> visitedLocations = entry.getValue();
			MLocation location = visitedLocations.get(visitedLocations.size() - 1).getLocation();
			allUsersLastLocation.put(userId, locationDTOConverter.convertEntityToDTO(location));
		}
		
		LOGGER.debug("getAllUsersLastLocation: returns " + allUsersLastLocation.size() + " items.");
		
		return allUsersLastLocation;
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public List<VisitedLocationDTO> getUserVisitedLocations(UUID userId) 
			throws UserNotFoundException, ConverterDTOException {
		LOGGER.debug("getUserVisitedLocations: userId=" + userId);
		
		Optional<List<MVisitedLocation>> optional = visitedLocationRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getUserVisitedLocations: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("User with userId=" + userId + " not found.");
		}
		
		LOGGER.debug("getUserVisitedLocations: returns " + optional.get().size() + " items.");
		
		return visitedLocationDTOConverter.convertVisitedLocationsToDTOs(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NearByAttractionDTO> getNearByAttractions(String username) 
			throws UserNotFoundException, HttpException, ConverterDTOException, ConverterLibException {
		LOGGER.debug("getNearByAttractions: username=" + username);
		
		UUID userId = userService.getUserId(username);
		
		Optional<List<MVisitedLocation>> optional = visitedLocationRepository.findByUserId(userId);
		if(!optional.isPresent()) {
			LOGGER.debug("getNearByAttractions: error: user with userId=" + userId + " not found.");
			throw new UserNotFoundException("User with userId=" + userId + " not found.");
		}
		
		List<NearByAttractionDTO> nearByAttractionDTOs = 
				nearByAttractionDTOConverter.convertNearByAttractionsToDTOs(
						nearByAttractionOperation.getNearByAttractions(userId));
		
		LOGGER.debug("getNearByAttractions: returns " + nearByAttractionDTOs.size() + " items.");
		
		return nearByAttractionDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MVisitedLocation trackUserLocation(UUID userId) 
			throws ConverterLibException, ConverterDTOException, HttpException {
		LOGGER.debug("trackUserLocation: userId=" + userId);
		
		// get current location
		MVisitedLocation mVisitedLocation = visitedLocationLibConverter.convertLibModelToModel(
				gpsUtil.getUserLocation(userId));
		
		// save current location
		visitedLocationRepository.save(mVisitedLocation);
		
		// call calculateRewards
		rewardService.calculateRewards(userId);
		
		return mVisitedLocation;
	}

}
