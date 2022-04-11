/**
 * 
 */
package com.tripmaster.tourguide.userService.dto;

import java.util.Date;
import java.util.UUID;

/**
 * User DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class UserDTO {

	private UUID userId;
	private String userName;
	private String phoneNumber;
	private String emailAddress;
	private Date latestLocationTimestamp;
	private PreferenceDTO preferenceDTO = new PreferenceDTO();
	
	public UserDTO() {
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
	 * @return the preferenceDTO
	 */
	public PreferenceDTO getPreferenceDTO() {
		return preferenceDTO;
	}
	/**
	 * @param preferenceDTO the preferenceDTO to set
	 */
	public void setPreferenceDTO(PreferenceDTO preferenceDTO) {
		this.preferenceDTO = preferenceDTO;
	}
	
	
}
