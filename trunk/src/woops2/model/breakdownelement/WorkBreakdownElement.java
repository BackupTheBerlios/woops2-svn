package woops2.model.breakdownelement;


/**
 * 
 * 
 */
public class WorkBreakdownElement extends BreakdownElement  {

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isRepeatable = false;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isOngoing = false;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isEvenDriven = false;

	public Boolean getIsEvenDriven() {
		return isEvenDriven;
	}

	public void setIsEvenDriven(Boolean isEvenDriven) {
		this.isEvenDriven = isEvenDriven;
	}

	public Boolean getIsOngoing() {
		return isOngoing;
	}

	public void setIsOngoing(Boolean isOngoing) {
		this.isOngoing = isOngoing;
	}

	public Boolean getIsRepeatable() {
		return isRepeatable;
	}

	public void setIsRepeatable(Boolean isRepeatable) {
		this.isRepeatable = isRepeatable;
	}
}
