package business.activity.state;

import business.activity.Activity;
import business.hibernate.HistorizedObject;


/**
 * @role __State
 */


public abstract class IActivityState extends HistorizedObject {
	protected	String name;
	
	/**
	 * Constructeur par d?faut appel? par Hibernate
	 */
	public IActivityState() {}
	
	/**
	 * Constructeur 
	 */
	public IActivityState(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean equals(String state) {
		return this.name.equals(state);
	}
	
	public boolean equals(Object obj) {
		return ((IActivityState)obj).getName().equals(name);
	}
	
	public abstract boolean process(Activity activity);
	public abstract boolean checkBeforeChange(Activity activity);
}