package woops2.model.breakdownelement;

import java.io.Serializable;

/**
 * 
 * 
 */
public class WorkBreakDownElement extends BreakDownElement implements
		Serializable {

	private static final long serialVersionUID = 8536408716131585279L;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	public Boolean isRepeatable = false;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	public Boolean isOngoing = false;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 */
	public Boolean isEvenDriven = false;
}
