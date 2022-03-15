/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * New LocationDTO for add operation.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewLocationDTO {

	@NotNull
	@DecimalMax(value = "90.0", message = "Latitude must be less than 90.")
	@DecimalMin(value = "-90.0", message = "Latitude must be more than -90.")
	private double latitude;
	
	@NotNull
	@DecimalMax(value = "180.0", message = "Longitude must be less than 180.")
	@DecimalMin(value = "-180.0", message = "Longitude must be more than -180.")
	private double longitude;
	
	public NewLocationDTO() {
		
	}
	
	public NewLocationDTO(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
