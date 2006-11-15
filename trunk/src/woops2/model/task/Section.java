
package woops2.model.task ;

import woops2.model.element.Element;

/**
 * @author Sebastien BALARD
 * 
 * This class represents a section which represents structural subsections of a task TODO
 * 
 */
public class Section extends Element {
	
	/**
	 * the attached task
	 */
	private Task task;
	
	/**
	 * Default constructor
	 */
	public Section() {}

	/**
	 * Getter of task.
	 *
	 * @return the task.
	 */
	public Task getTask () {
		return this.task ;
	}

	/**
	 * Setter of task.
	 *
	 * @param _task The task to set.
	 */
	public void setTask (Task _task) {
		this.task = _task ;
	}
	
	

}
