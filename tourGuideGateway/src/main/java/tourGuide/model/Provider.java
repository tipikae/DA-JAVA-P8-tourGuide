/**
 * 
 */
package tourGuide.model;

import java.util.UUID;

/**
 * Provider model.
 * @author tipikae
 * @version 1.0
 *
 */
public class Provider {

	private String name;
	private double price;
	private UUID tripId;
	
	public Provider() {
	}
	
	public Provider(String name, double price, UUID tripId) {
		this.name = name;
		this.price = price;
		this.tripId = tripId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the tripId
	 */
	public UUID getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(UUID tripId) {
		this.tripId = tripId;
	}
	
}
