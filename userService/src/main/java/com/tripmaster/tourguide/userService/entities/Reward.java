/**
 * 
 */
package com.tripmaster.tourguide.userService.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Reward entity.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Data
@Table(name = "rewards")
public class Reward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visitedLocation", referencedColumnName = "id")
	private VisitedLocation visitedLocation;
	
	@Column(name = "points")
	private int rewardPoints;
	
	@Column(name = "attraction_id")
	private String attractionId;
	
	@Column(name = "attraction_name")
	private String attractionName;
	
	@Column(name = "attraction_city")
	private String attractionCity;
	
	@Column(name = "attraction_state")
	private String attractionState;
}
