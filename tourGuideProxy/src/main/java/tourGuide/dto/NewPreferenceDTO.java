package tourGuide.dto;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.javamoney.moneta.Money;

/**
 * New Preference DTO
 * @author tipikae
 * @version 1.0
 *
 */
public class NewPreferenceDTO {

	private int attractionProximity = Integer.MAX_VALUE;
	private CurrencyUnit currency = Monetary.getCurrency("USD");
	private Money lowerPricePoint = Money.of(0, currency);
	private Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);
	
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
	 * @return the attractionProximity
	 */
	public int getAttractionProximity() {
		return attractionProximity;
	}

	/**
	 * @param attractionProximity the attractionProximity to set
	 */
	public void setAttractionProximity(int attractionProximity) {
		this.attractionProximity = attractionProximity;
	}

	/**
	 * @return the currency
	 */
	public CurrencyUnit getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	/**
	 * @return the lowerPricePoint
	 */
	public Money getLowerPricePoint() {
		return lowerPricePoint;
	}

	/**
	 * @param lowerPricePoint the lowerPricePoint to set
	 */
	public void setLowerPricePoint(Money lowerPricePoint) {
		this.lowerPricePoint = lowerPricePoint;
	}

	/**
	 * @return the highPricePoint
	 */
	public Money getHighPricePoint() {
		return highPricePoint;
	}

	/**
	 * @param highPricePoint the highPricePoint to set
	 */
	public void setHighPricePoint(Money highPricePoint) {
		this.highPricePoint = highPricePoint;
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
