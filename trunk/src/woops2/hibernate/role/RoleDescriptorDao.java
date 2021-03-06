package woops2.hibernate.role;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.role.RoleDescriptor;

/**
 * RoleDefinitionDao manage requests from the system to store RoleDefinition to the database
 * 
 * @author soosuske
 */
public class RoleDescriptorDao extends HibernateDaoSupport {

	/**
	 * Save or update an RoleDescriptor
	 * 
	 * @param _RoleDescriptor
	 */
	public void saveOrUpdateRoleDescriptor(RoleDescriptor _RoleDescriptor) {
		this.getHibernateTemplate().saveOrUpdate(_RoleDescriptor);
	}

	/**
	 * Return a set of RoleDescriptor
	 * 
	 * @return
	 */
	public Set<RoleDescriptor> getAllRoleDescriptor() {
		Set<RoleDescriptor> loadAll = new HashSet<RoleDescriptor>();
		loadAll.addAll(this.getHibernateTemplate()
				.loadAll(RoleDescriptor.class));
		return loadAll;
	}

	/**
	 * Return the RoleDescriptor which have the id _id
	 * 
	 * @param _id
	 * @return
	 */
	public RoleDescriptor getRoleDescriptor(String _id) {
		return (RoleDescriptor) this.getHibernateTemplate().get(
				RoleDescriptor.class, _id);
	}

	/**
	 * Delete the RoleDescriptor
	 * 
	 * @param _RoleDescriptor
	 */
	public void deleteRoleDescriptor(RoleDescriptor _RoleDescriptor) {
		try {
			this.getHibernateTemplate().delete(_RoleDescriptor);
		} catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting RoleDescriptor
			// into the db.
			logger
					.error("#### ERROR #### --- RoleDescriptorDao => deleteRoleDescriptor : trying to delete unexisting object \n"
							+ sose);
		}
	}
}
