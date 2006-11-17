
package woops2.hibernate.role ;

import java.util.HashSet ;
import java.util.Set ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.role.RoleDefinition ;

/**
 * RoleDefinitionDao manage requests from the system to store RoleDefinition to the database
 * 
 * @author soosuske
 */
public class RoleDefinitionDao extends HibernateDaoSupport {

	/**
	 * Save or update an role
	 * 
	 * @param _roleDefinition
	 */
	public void saveOrUpdateRole(RoleDefinition _roleDefinition) {
		this.getHibernateTemplate().saveOrUpdate(_roleDefinition) ;
	}

	/**
	 * Return a list of role
	 * 
	 * @return
	 */
	public Set<RoleDefinition> getAllRole() {
		Set<RoleDefinition> loadAll = new HashSet<RoleDefinition>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(RoleDefinition.class)) ;
		return loadAll ;
	}

	/**
	 * Return the role which have the id _id
	 * 
	 * @param _id
	 * @return
	 */
	public RoleDefinition getRole(String _id) {
		return (RoleDefinition) this.getHibernateTemplate().get(RoleDefinition.class, _id) ;
	}

	/**
	 * Delete the role
	 * 
	 * @param _roleDefinition
	 */
	public void deleteRole(RoleDefinition _roleDefinition) {
		try{
			this.getHibernateTemplate().delete(_roleDefinition) ;
		}
		catch(StaleObjectStateException e){
			// Catch normally errors when we delete an unexisting role into the
			// db.
			logger.error("#### ERROR #### --- RoleDefinitionDao => deleteRole : trying to delete unexisting object \n" + e) ;
		}
	}
}
