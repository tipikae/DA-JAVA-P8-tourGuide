/**
 * 
 */
package com.tripmaster.tourguide.userService.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import tripPricer.Provider;

/**
 * New User DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class NewUserDTO {

	@NotNull
	private UUID userId;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	private String emailAddress;
	
	private Date latestLocationTimestamp;
	private NewPreferenceDTO preference = new NewPreferenceDTO();
	private List<Provider> tripDeals = new ArrayList<>();
	
	public NewUserDTO() {
		
	}
	
	public NewUserDTO(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the userId
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the preference
	 */
	public NewPreferenceDTO getPreference() {
		return preference;
	}

	/**
	 * @param preference the preference to set
	 */
	public void setPreference(NewPreferenceDTO preference) {
		this.preference = preference;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the latestLocationTimestamp
	 */
	public Date getLatestLocationTimestamp() {
		return latestLocationTimestamp;
	}

	/**
	 * @param latestLocationTimestamp the latestLocationTimestamp to set
	 */
	public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
		this.latestLocationTimestamp = latestLocationTimestamp;
	}

	/**
	 * @return the tripDeals
	 */
	public List<Provider> getTripDeals() {
		return tripDeals;
	}

	/**
	 * @param tripDeals the tripDeals to set
	 */
	public void setTripDeals(List<Provider> tripDeals) {
		this.tripDeals = tripDeals;
	}
}
