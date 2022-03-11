/**
 * 
 */
package com.tripmaster.tourguide.userService.model;

import java.util.UUID;

import lombok.Data;

/**
 * Attraction model.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
public class Attraction extends Location {

	private UUID attractionId;
	private String attractionName;
	private String city;
	private String state;
}
