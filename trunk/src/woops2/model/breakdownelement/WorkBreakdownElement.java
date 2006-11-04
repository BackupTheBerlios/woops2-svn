
package woops2.model.breakdownelement ;

/**
 * 
 * 
 */
public class WorkBreakdownElement extends BreakdownElement {

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isRepeatable = false ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isOngoing = false ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isEvenDriven = false ;

	public Boolean getIsEvenDriven () {
		return this.isEvenDriven ;
	}

	public void setIsEvenDriven (Boolean _isEvenDriven) {
		this.isEvenDriven = _isEvenDriven ;
	}

	public Boolean getIsOngoing () {
		return this.isOngoing ;
	}

	public void setIsOngoing (Boolean _isOngoing) {
		this.isOngoing = _isOngoing ;
	}

	public Boolean getIsRepeatable () {
		return this.isRepeatable ;
	}

	public void setIsRepeatable (Boolean _isRepeatable) {
		this.isRepeatable = _isRepeatable ;
	}
}
