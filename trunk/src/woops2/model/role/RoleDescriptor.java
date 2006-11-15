
package woops2.model.role ;

import woops2.model.breakdownelement.BreakdownElement ;

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

	public RoleDescriptor () {
		super() ;
	}

	/**
	 * Getter of role.
	 * 
	 * @return the role.
	 */
	public Role getRole () {
		return this.role ;
	}

	/**
	 * Setter of role.
	 * 
	 * @param _role
	 *            The role to set.
	 */
	public void setRole (Role _role) {
		this.role = _role ;
	}
}
