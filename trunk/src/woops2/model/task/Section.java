
package woops2.model.task ;

import woops2.model.element.Element ;

/**
 * @author Sebastien BALARD
 * 
 * This class represents a section which represents structural subsections of a taskDefinition TODO
 * 
 */
public class Section extends Element {

	/**
	 * the attached taskDefinition
	 */
	private TaskDefinition taskDefinition ;

	/**
	 * Default constructor
	 */
	public Section() {
	}

	/**
	 * Getter of taskDefinition.
	 * 
	 * @return the taskDefinition.
	 */
	public TaskDefinition getTaskDefinition() {
		return this.taskDefinition ;
	}

	/**
	 * Setter of taskDefinition.
	 * 
	 * @param _taskDefinition
	 *            The taskDefinition to set.
	 */
	public void setTaskDefinition(TaskDefinition _taskDefinition) {
		this.taskDefinition = _taskDefinition ;
	}

}
