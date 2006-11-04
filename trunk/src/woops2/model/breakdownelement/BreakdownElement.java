
package woops2.model.breakdownelement ;

import java.util.HashSet ;

import woops2.model.activity.Activity ;

public class BreakdownElement {

	private String id ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private String prefix ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isPlanned = true ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean hasMultipleOccurrences = false ;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isOptional = false ;

	/**
	 * 
	 * @poseidon-type Activity
	 */
	private java.util.Set <Activity> superActivities = new HashSet <Activity>() ;

	public Boolean getHasMultipleOccurrences () {
		return this.hasMultipleOccurrences ;
	}

	public void setHasMultipleOccurrences (Boolean _hasMultipleOccurrences) {
		this.hasMultipleOccurrences = _hasMultipleOccurrences ;
	}

	public String getId () {
		return this.id ;
	}

	public void setId (String _id) {
		this.id = _id ;
	}

	public Boolean getIsOptional () {
		return this.isOptional ;
	}

	public void setIsOptional (Boolean _isOptional) {
		this.isOptional = _isOptional ;
	}

	public Boolean getIsPlanned () {
		return this.isPlanned ;
	}

	public void setIsPlanned (Boolean _isPlanned) {
		this.isPlanned = _isPlanned ;
	}

	public String getPrefix () {
		return this.prefix ;
	}

	public void setPrefix (String _prefix) {
		this.prefix = _prefix ;
	}

	public java.util.Set getSuperActivities () {
		return this.superActivities ;
	}

	public void setSuperActivities (java.util.Set <Activity> _superActivities) {
		this.superActivities = _superActivities ;
	}
}
