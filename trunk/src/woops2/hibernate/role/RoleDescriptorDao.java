
package woops2.hibernate.role ;

import java.util.ArrayList ;
import java.util.List ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.role.RoleDescriptor ;

/**
 * RoleDao manage requests from the system to store Role to the database
 * 
 * @author soosuske
 */
public class RoleDescriptorDao extends HibernateDaoSupport {

	/**
	 * Save or update an RoleDescriptor
	 * 
	 * @param _RoleDescriptor
	 */
	public void saveOrUpdateRoleDescriptor (RoleDescriptor _RoleDescriptor) {
		this.getHibernateTemplate().saveOrUpdate(_RoleDescriptor) ;
	}

	/**
	 * Return a list of RoleDescriptor
	 * 
	 * @return
	 */
	public List <RoleDescriptor> getAllRoleDescriptor () {
		List <RoleDescriptor> loadAll = new ArrayList <RoleDescriptor>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(RoleDescriptor.class)) ;
		return loadAll ;
	}

	/**
	 * Return the RoleDescriptor which have the id _id
	 * 
	 * @param _id
	 * @return
	 */
	public RoleDescriptor getRoleDescriptor (String _id) {
		return (RoleDescriptor) this.getHibernateTemplate().get(RoleDescriptor.class, _id) ;
	}

	/**
	 * Delete the RoleDescriptor
	 * 
	 * @param _RoleDescriptor
	 */
	public void deleteRoleDescriptor (RoleDescriptor _RoleDescriptor) {
		try {
			this.getHibernateTemplate().delete(_RoleDescriptor) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting RoleDescriptor into the db.
			logger.error("#### ERROR #### --- RoleDescriptorDao => deleteRoleDescriptor : trying to delete unexisting object \n"+sose);
		}
	}
}
