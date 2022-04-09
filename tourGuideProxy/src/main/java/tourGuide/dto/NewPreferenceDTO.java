package tourGuide.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * New Preference DTO
 * @author tipikae
 * @version 1.0
 *
 */
public class NewPreferenceDTO {
	
	@NotNull
	@Positive
	private int tripDuration = 1;
	
	@NotNull
	@Positive
	private int ticketQuantity = 1;
	
	@NotNull
	@Positive
	private int numberOfAdults = 1;
	
	@NotNull
	@Positive
	private int numberOfChildren = 0;
	
	public NewPreferenceDTO() {
	}

	/**
	 * @return the tripDuration
	 */
	public int getTripDuration() {
		return tripDuration;
	}

	/**
	 * @param tripDuration the tripDuration to set
	 */
	public void setTripDuration(int tripDuration) {
		this.tripDuration = tripDuration;
	}

	/**
	 * @return the ticketQuantity
	 */
	public int getTicketQuantity() {
		return ticketQuantity;
	}

	/**
	 * @param ticketQuantity the ticketQuantity to set
	 */
	public void setTicketQuantity(int ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}

	/**
	 * @return the numberOfAdults
	 */
	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	/**
	 * @param numberOfAdults the numberOfAdults to set
	 */
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	/**
	 * @return the numberOfChildren
	 */
	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @param numberOfChildren the numberOfChildren to set
	 */
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
}
