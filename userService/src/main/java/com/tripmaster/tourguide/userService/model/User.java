/**
 * 
 */
package com.tripmaster.tourguide.userService.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

/**
 * User model.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Data
public class User {

	private int id;
	private String userId;
	private String userName;
	private String phoneNumber;
	private String emailAddress;
	private Date latestLocationTimestamp;
	private int attractionProximity;
	private String currency;
	private int lowerPricePoint;
	private int highPricePoint;
	private int tripDuration;
	private int ticketQuantity;
	private int numberOfAdults;
	private int numberOfChildren;
}
