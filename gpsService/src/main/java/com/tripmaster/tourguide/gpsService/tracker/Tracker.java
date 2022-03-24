package com.tripmaster.tourguide.gpsService.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripmaster.tourguide.gpsService.exceptions.ConverterDTOException;
import com.tripmaster.tourguide.gpsService.exceptions.ConverterLibException;
import com.tripmaster.tourguide.gpsService.exceptions.HttpException;
import com.tripmaster.tourguide.gpsService.remoteServices.IUserService;
import com.tripmaster.tourguide.gpsService.service.IGpsServiceService;

/**
 * Tracker thread runs every 5 minutes.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class Tracker extends Thread {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
	private static final long TRACKING_POLLING_INTERVAL = TimeUnit.MINUTES.toSeconds(5);
	
	@Autowired
	private IGpsServiceService gpsService;
	
	@Autowired
	private IUserService userService;
	
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	private boolean stop = false;

	public Tracker() {
		addShutDownHook();
		executorService.submit(this);
	}
	
	/**
	 * Assures to shut down the Tracker thread.
	 */
	public void stopTracking() {
		LOGGER.debug("stopTracking");
		stop = true;
		executorService.shutdownNow();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		StopWatch stopWatch = new StopWatch();
		
		while(true) {
			if(Thread.currentThread().isInterrupted() || stop) {
				LOGGER.debug("run: Tracker stopping");
				break;
			}
			
			List<UUID> userIds = new ArrayList<>();
			try {
				userIds = userService.getAllUserIds();
			} catch (HttpException e) {
				LOGGER.debug("run: userService failed to retrieve all userIds.");
			}
			
			LOGGER.debug("run: Begin Tracker. Tracking " + userIds.size() + " users.");
			
			stopWatch.start();
			userIds.forEach(userId -> {
				try {
					gpsService.trackUserLocation(userId);
				} catch (ConverterLibException | ConverterDTOException | HttpException e) {
					LOGGER.debug("run: gpsService failed to track user location: userId=" + userId);
				}
			});
			stopWatch.stop();
			
			LOGGER.debug("run: Tracker Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) 
			+ " seconds."); 
			stopWatch.reset();
			
			try {
				LOGGER.debug("run: Tracker sleeping");
				TimeUnit.SECONDS.sleep(TRACKING_POLLING_INTERVAL);
			} catch (InterruptedException e) {
				break;
			}
		}
		
	}
	
	private void addShutDownHook() {
		LOGGER.debug("addShutDownHook");
		Runtime.getRuntime().addShutdownHook(new Thread() { 
		      public void run() {
		        stopTracking();
		      } 
		    }); 
	}
}
