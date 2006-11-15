
package woops2.model.activity ;

import woops2.model.workbreakdownelement.WorkBreakdownElement ;

/**
 * @author Mathieu BENOIT.
 * 
 * This class represents ... TODO
 * 
 */
public class Activity extends WorkBreakdownElement {

	private java.util.Set breakDownElements = new java.util.TreeSet() ;

	/**
	 * Constructor.
	 * 
	 */
	public Activity () {
		super() ;
	}

	/**
	 * Getter of breakDownElements.
	 * 
	 * @return the breakDownElements.
	 */
	public java.util.Set getBreakDownElements () {
		return this.breakDownElements ;
	}

	/**
	 * Setter of breakDownElements.
	 * 
	 * @param _breakDownElements
	 *            The breakDownElements to set.
	 */
	public void setBreakDownElements (java.util.Set _breakDownElements) {
		this.breakDownElements = _breakDownElements ;
	}

}
