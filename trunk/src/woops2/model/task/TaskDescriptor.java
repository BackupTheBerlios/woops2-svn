package woops2.model.task;

import woops2.model.workbreakdownelement.WorkBreakdownElement;

/**
 * @author Sebastien BALARD
 *
 * This class represents ... TODO
 *
 */
public class TaskDescriptor extends WorkBreakdownElement {
	
	/**
	 * the attached task
	 */
	private Task task;
	
	/**
	 * Default constructor.
	 */
	public TaskDescriptor() {
		super();
	}

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
