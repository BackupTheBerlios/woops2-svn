
package woops2.model.breakdownelement ;

/**
 * @author Administrateur
 * 
 * This class represents ... TODO
 * 
 */
public class WorkBreakdownElement extends BreakdownElement {

	private Boolean isRepeatable = false ;

	private Boolean isOngoing = false ;

	private Boolean isEvenDriven = false ;

	/**
	 * Getter of isEvenDriven.
	 * 
	 * @return the isEvenDriven.
	 */
	public Boolean getIsEvenDriven () {
		return this.isEvenDriven ;
	}

	/**
	 * Setter of isEvenDriven.
	 * 
	 * @param _isEvenDriven
	 *            The isEvenDriven to set.
	 */
	public void setIsEvenDriven (Boolean _isEvenDriven) {
		this.isEvenDriven = _isEvenDriven ;
	}

	/**
	 * Getter of isOngoing.
	 * 
	 * @return the isOngoing.
	 */
	public Boolean getIsOngoing () {
		return this.isOngoing ;
	}

	/**
	 * Setter of isOngoing.
	 * 
	 * @param _isOngoing
	 *            The isOngoing to set.
	 */
	public void setIsOngoing (Boolean _isOngoing) {
		this.isOngoing = _isOngoing ;
	}

	/**
	 * Getter of isRepeatable.
	 * 
	 * @return the isRepeatable.
	 */
	public Boolean getIsRepeatable () {
		return this.isRepeatable ;
	}

	/**
	 * Setter of isRepeatable.
	 * 
	 * @param _isRepeatable
	 *            The isRepeatable to set.
	 */
	public void setIsRepeatable (Boolean _isRepeatable) {
		this.isRepeatable = _isRepeatable ;
	}
}
