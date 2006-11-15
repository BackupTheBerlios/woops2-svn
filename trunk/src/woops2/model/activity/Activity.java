
package woops2.model.activity ;

import woops2.model.workbreakdownelement.WorkBreakdownElement ;

/**
 * @author deder.
 * 
 * An Activity is a Work Breakdown Element and Work Definition which supports the nesting and
 * logical grouping of related Breakdown Elements forming breakdown structures. Although Activity is
 * a concrete meta-class, other classes which represent breakdown structures derive from it; such as
 * Phase, Iteration, Delivery Process, or Capability Pattern.
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
