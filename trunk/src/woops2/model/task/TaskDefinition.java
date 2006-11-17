
package woops2.model.task ;

import java.util.HashSet ;
import java.util.Set ;

import woops2.model.element.Element ;

/**
 * @author Sebastien BALARD
 * 
 * This class represents a task is a content element that describes work being performed by Roles.
 * It defines one default performing RoleDefinition as well as many additional performers... TODO
 * 
 */
public class TaskDefinition extends Element {

	/**
	 * Collection of Step
	 */
	private Set<Step> steps ;

	/**
	 * Collection of TaskDescriptor
	 */
	private Set<TaskDescriptor> taskDescriptors ;

	/**
	 * Default constructor
	 */
	public TaskDefinition() {
		super() ;
		this.steps = new HashSet<Step>() ;
		this.taskDescriptors = new HashSet<TaskDescriptor>() ;
	}

	/**
	 * Getter of steps.
	 * 
	 * @return the steps.
	 */
	public Set<Step> getSteps() {
		return this.steps ;
	}

	/**
	 * Setter of steps.
	 * 
	 * @param _steps
	 *            The steps to set.
	 */
	public void setSteps(Set<Step> _steps) {
		this.steps = _steps ;
	}

	/**
	 * Getter of taskDescriptors.
	 * 
	 * @return the taskDescriptors.
	 */
	public Set<TaskDescriptor> getTaskDescriptors() {
		return this.taskDescriptors ;
	}

	/**
	 * Setter of taskDescriptors.
	 * 
	 * @param _taskDescriptors
	 *            The taskDescriptors to set.
	 */
	public void setTaskDescriptors(Set<TaskDescriptor> _taskDescriptors) {
		this.taskDescriptors = _taskDescriptors ;
	}
}
