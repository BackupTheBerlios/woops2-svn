
package woops2.model.workbreakdownelement ;

import woops2.model.breakdownelement.BreakdownElement ;

/**
 * @author deder
 * 
 * A Work Breakdown Element is a special Breakdown Element that provides specific properties for
 * Breakdown Elements that represent or refer to Work Definitions.
 * 
 */
public class WorkBreakdownElement extends BreakdownElement {

	private Boolean isRepeatable ;

	private Boolean isOngoing ;

	private Boolean isEvenDriven ;

	public WorkBreakdownElement () {
		super() ;
		this.isEvenDriven = false ;
		this.isOngoing = false ;
		this.isRepeatable = false ;
	}

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
