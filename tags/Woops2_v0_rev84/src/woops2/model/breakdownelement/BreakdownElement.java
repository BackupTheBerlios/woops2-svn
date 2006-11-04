package woops2.model.breakdownelement;

import java.util.HashSet;

import woops2.model.activity.Activity;

public class BreakdownElement {

	private String id;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private String prefix;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isPlanned = true;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean hasMultipleOccurrences = false;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	private Boolean isOptional = false;

	/**
	 * 
	 * @poseidon-type Activity
	 */
	private java.util.Set<Activity> superActivities = new HashSet<Activity>();

	public Boolean getHasMultipleOccurrences() {
		return hasMultipleOccurrences;
	}

	public void setHasMultipleOccurrences(Boolean hasMultipleOccurrences) {
		this.hasMultipleOccurrences = hasMultipleOccurrences;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsOptional() {
		return isOptional;
	}

	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
	}

	public Boolean getIsPlanned() {
		return isPlanned;
	}

	public void setIsPlanned(Boolean isPlanned) {
		this.isPlanned = isPlanned;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public java.util.Set getSuperActivities() {
		return superActivities;
	}

	public void setSuperActivities(java.util.Set<Activity> superActivities) {
		this.superActivities = superActivities;
	}
}
