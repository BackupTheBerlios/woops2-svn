
package woops2.hibernate.role ;

import java.util.ArrayList ;
import java.util.List ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.role.Role ;

/**
 * RoleDao manage requests from the system to store Role to the database
 * 
 * @author soosuske
 */
public class RoleDao extends HibernateDaoSupport {

	/**
	 * Save or update an role
	 * 
	 * @param _role
	 */
	public void saveOrUpdateRole (Role _role) {
		this.getHibernateTemplate().saveOrUpdate(_role) ;
	}

	/**
	 * Return a list of role
	 * 
	 * @return
	 */
	public List <Role> getAllRole () {
		List <Role> loadAll = new ArrayList <Role>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Role.class)) ;
		return loadAll ;
	}

	/**
	 * Return the role which have the id _id
	 * 
	 * @param _id
	 * @return
	 */
	public Role getRole (String _id) {
		return (Role) this.getHibernateTemplate().get(Role.class, _id) ;
	}

	/**
	 * Delete the role
	 * 
	 * @param _role
	 */
	public void deleteRole (Role _role) {
		try {
			this.getHibernateTemplate().delete(_role) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting role into the db.
			logger.error("#### ERROR #### --- RoleDao => deleteRole : trying to delete unexisting object \n"+sose);
		}
	}
}
