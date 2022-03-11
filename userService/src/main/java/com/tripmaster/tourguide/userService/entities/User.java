/**
 * 
 */
package com.tripmaster.tourguide.userService.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * User entity.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "userid",
			unique = true)
	private String userId;
	
	@Column(name = "username",
			unique = true)
	private String userName;
	
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@Column(name = "email",
			unique = true)
	private String emailAddress;
	
	@Column(name = "latestlocationtimestamp")
	private Date latestLocationTimestamp;
	
	@Column(name = "attractionproximity")
	private int attractionProximity;
	
	private String currency;
	
	@Column(name = "lowerpricepoint")
	private int lowerPricePoint;
	
	@Column(name = "highpricepoint")
	private int highPricePoint;
	
	@Column(name = "tripduration")
	private int tripDuration;
	
	@Column(name = "ticketquantity")
	private int ticketQuantity;
	
	@Column(name = "adults")
	private int numberOfAdults;
	
	@Column(name = "children")
	private int numberOfChildren;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Reward> rewards;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<VisitedLocation> visitedLocations;
	
}
