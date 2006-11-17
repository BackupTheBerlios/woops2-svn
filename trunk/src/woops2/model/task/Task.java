
package woops2.model.task ;

import java.util.HashSet ;
import java.util.Set ;

import woops2.model.element.Element ;

/**
 * @author Sebastien BALARD
 * 
 * This class represents a task is a content element that describes work being performed by Roles.
 * It defines one default performing Role as well as many additional performers... TODO
 * 
 */
public class Task extends Element {

	/**
	 * Collection of Section
	 */
	private Set <Section> sections ;

	/**
	 * Collection of TaskDescriptor
	 */
	private Set <TaskDescriptor> taskDescriptors ;

	/**
	 * Default constructor
	 */
	public Task () {
		super();
		this.sections = new HashSet <Section>() ;
		this.taskDescriptors = new HashSet <TaskDescriptor>() ;
	}

	/**
	 * Getter of sections.
	 * 
	 * @return the sections.
	 */
	public Set <Section> getSections () {
		return this.sections ;
	}

	/**
	 * Setter of sections.
	 * 
	 * @param _sections
	 *            The sections to set.
	 */
	public void setSections (Set <Section> _sections) {
		this.sections = _sections ;
	}

	/**
	 * Getter of taskDescriptors.
	 * 
	 * @return the taskDescriptors.
	 */
	public Set <TaskDescriptor> getTaskDescriptors () {
		return this.taskDescriptors ;
	}

	/**
	 * Setter of taskDescriptors.
	 * 
	 * @param _taskDescriptors
	 *            The taskDescriptors to set.
	 */
	public void setTaskDescriptors (Set <TaskDescriptor> _taskDescriptors) {
		this.taskDescriptors = _taskDescriptors ;
	}
}