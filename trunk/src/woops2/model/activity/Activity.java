
package woops2.model.activity ;

import java.util.HashSet ;
import java.util.Set ;

import woops2.model.breakdownelement.BreakdownElement ;
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

	private Set <BreakdownElement> breakDownElements ;

	/**
	 * Constructor.
	 * 
	 */
	public Activity () {
		super() ;
		this.breakDownElements = new HashSet <BreakdownElement>() ;
	}

	/**
	 * Getter of breakDownElements.
	 * 
	 * @return the breakDownElements.
	 */
	public Set <BreakdownElement> getBreakDownElements () {
		return this.breakDownElements ;
	}

	/**
	 * Setter of breakDownElements.
	 * 
	 * @param _breakDownElements
	 *            The breakDownElements to set.
	 */
	public void setBreakDownElements (Set <BreakdownElement> _breakDownElements) {
		this.breakDownElements = _breakDownElements ;
	}

}
