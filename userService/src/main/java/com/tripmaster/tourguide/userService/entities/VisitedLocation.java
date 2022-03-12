/**
 * 
 */
package com.tripmaster.tourguide.userService.entities;

import java.util.Date;

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
 * VisitedLocation model.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Data
@Table(name = "visited_locations")
public class VisitedLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@OneToOne(mappedBy = "visitedLocation")
	private Reward reward;
	
	private double latitude;
	
	private double longitude;
	
	@Column(name = "timevisited")
	private Date timeVisited;
}
