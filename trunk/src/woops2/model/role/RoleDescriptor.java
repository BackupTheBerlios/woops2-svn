
package woops2.model.role ;

import java.util.Set ;

import woops2.model.breakdownelement.BreakdownElement ;
import woops2.model.task.TaskDescriptor ;

/**
 * @author deder
 * 
 * A Role Descriptor represents a Role in the context of one specific Activity. Every breakdown
 * structure can define different relationships of Role Descriptors to Task Descriptors and Work
 * Product Descriptors. Therefore one Role can be represented by many Role Descriptors each within
 * the context of an Activity with its own set of relationships.
 * 
 */
public class RoleDescriptor extends BreakdownElement {

	private Role role ;

	public RoleDescriptor() {
		super() ;
	}

	/**
	 * The main tasks of the role
	 */
	private Set<TaskDescriptor> primaryTasks ;

	/**
	 * The additional tasks of the role
	 */
	private Set<TaskDescriptor> additionalTasks ;

	/**
	 * Getter of role.
	 * 
	 * @return the role.
	 */
	public Role getRole() {
		return this.role ;
	}

	/**
	 * Setter of role.
	 * 
	 * @param _role
	 *            The role to set.
	 */
	public void setRole(Role _role) {
		this.role = _role ;
	}

	/**
	 * Getter of primaryTasks.
	 * 
	 * @return the primaryTasks.
	 */
	public Set<TaskDescriptor> getPrimaryTasks() {
		return this.primaryTasks ;
	}

	/**
	 * Setter of primaryTasks.
	 * 
	 * @param _primaryTasks
	 *            The primaryTasks to set.
	 */
	public void setPrimaryTasks(Set<TaskDescriptor> _primaryTasks) {
		this.primaryTasks = _primaryTasks ;
	}

	/**
	 * Getter of additionalTasks.
	 * 
	 * @return the additionalTasks.
	 */
	public Set<TaskDescriptor> getAdditionalTasks() {
		return this.additionalTasks ;
	}

	/**
	 * Setter of additionalTasks.
	 * 
	 * @param _additionalTasks
	 *            The additionalTasks to set.
	 */
	public void setAdditionalTasks(Set<TaskDescriptor> _additionalTasks) {
		this.additionalTasks = _additionalTasks ;
	}
}
