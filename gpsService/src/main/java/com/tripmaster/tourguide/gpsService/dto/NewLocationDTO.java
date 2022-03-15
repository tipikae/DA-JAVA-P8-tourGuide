/**
 * 
 */
package com.tripmaster.tourguide.gpsService.dto;

/**
 * New LocationDTO for add operation.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewLocationDTO {

	private double latitude;
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
