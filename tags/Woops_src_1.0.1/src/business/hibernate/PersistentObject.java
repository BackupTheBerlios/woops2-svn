package business.hibernate;

import java.io.Serializable;

/**
 * @author Nicolas Ricard
 * 
 */
public interface PersistentObject extends Serializable {
	
	public Object getId();
	public void setId(Object id);
	
	public String toString();
	
}