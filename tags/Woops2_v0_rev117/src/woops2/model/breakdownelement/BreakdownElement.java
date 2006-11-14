
package woops2.model.breakdownelement ;

import java.util.HashSet ;

import woops2.model.activity.Activity ;

/**
 * @author Mathieu BENOIT.
 * 
 * This class represents ... TODO
 * 
 */
public class BreakdownElement {

	private String id ;

	private String prefix ;

	private Boolean isPlanned = true ;

	private Boolean hasMultipleOccurrences = false ;

	private Boolean isOptional = false ;

	private java.util.Set <Activity> superActivities = new HashSet <Activity>() ;

	/**
	 * Getter of hasMultipleOccurrences.
	 * 
	 * @return the hasMultipleOccurrences.
	 */
	public Boolean getHasMultipleOccurrences () {
		return this.hasMultipleOccurrences ;
	}

	/**
	 * Setter of hasMultipleOccurrences.
	 * 
	 * @param _hasMultipleOccurrences
	 *            The hasMultipleOccurrences to set.
	 */
	public void setHasMultipleOccurrences (Boolean _hasMultipleOccurrences) {
		this.hasMultipleOccurrences = _hasMultipleOccurrences ;
	}

	/**
	 * Getter of id.
	 * 
	 * @return the id.
	 */
	public String getId () {
		return this.id ;
	}

	/**
	 * Setter of id.
	 * 
	 * @param _id
	 *            The id to set.
	 */
	public void setId (String _id) {
		this.id = _id ;
	}

	/**
	 * Getter of isOptional.
	 * 
	 * @return the isOptional.
	 */
	public Boolean getIsOptional () {
		return this.isOptional ;
	}

	/**
	 * Setter of isOptional.
	 * 
	 * @param _isOptional
	 *            The isOptional to set.
	 */
	public void setIsOptional (Boolean _isOptional) {
		this.isOptional = _isOptional ;
	}

	/**
	 * Getter of isPlanned.
	 * 
	 * @return the isPlanned.
	 */
	public Boolean getIsPlanned () {
		return this.isPlanned ;
	}

	/**
	 * Setter of isPlanned.
	 * 
	 * @param _isPlanned
	 *            The isPlanned to set.
	 */
	public void setIsPlanned (Boolean _isPlanned) {
		this.isPlanned = _isPlanned ;
	}

	/**
	 * Getter of prefix.
	 * 
	 * @return the prefix.
	 */
	public String getPrefix () {
		return this.prefix ;
	}

	/**
	 * Setter of prefix.
	 * 
	 * @param _prefix
	 *            The prefix to set.
	 */
	public void setPrefix (String _prefix) {
		this.prefix = _prefix ;
	}

	/**
	 * Getter of superActivities.
	 * 
	 * @return the superActivities.
	 */
	public java.util.Set <Activity> getSuperActivities () {
		return this.superActivities ;
	}

	/**
	 * Setter of superActivities.
	 * 
	 * @param _superActivities
	 *            The superActivities to set.
	 */
	public void setSuperActivities (java.util.Set <Activity> _superActivities) {
		this.superActivities = _superActivities ;
	}
}
