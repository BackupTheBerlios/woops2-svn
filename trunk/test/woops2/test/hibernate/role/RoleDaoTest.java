
package woops2.test.hibernate.role ;

import java.util.List ;

import woops2.hibernate.role.RoleDao ;
import woops2.model.role.Role ;
import woops2.test.TestConfiguration ;

/**
 * Unit test for RoleDao
 * 
 * @author Soosuske
 */
public class RoleDaoTest extends TestConfiguration {

	private RoleDao roleDao = null ;

	private Role role = null ;

	public static final String NAME = "thisRole" ;

	public static final String DESCRIPTION = "role description" ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp () throws Exception {
		super.setUp() ;

		// Get the RoleDao Singleton for managing Role data
		this.roleDao = (RoleDao) super.getBeanFactory().getBean("RoleDao") ;

		// Create empty Role
		this.role = new Role() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown () throws Exception {
		super.tearDown() ;

		// Delete the tmp role from the database.
		try {
			this.roleDao.getHibernateTemplate().delete(this.role) ;
		}
		catch (Exception exception) {
			// None.
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.role.RoleDao#saveOrUpdateRole(woops2.model.role.Role)}.
	 * 
	 * PRINCIPLE Create a tmp role, save it into the database with the method to test. Then look
	 * for the database to check if this tmp role exists. To finish delete this tmp role
	 * from the database.
	 */
	public void testSaveOrUpdateRole () {
		// Rk: the setUp method is called here.

		// Save the role with the method to test.
		this.roleDao.saveOrUpdateRole(this.role) ;

		// Check the saving.
		String id = role.getId() ;
		Role roleTmp = (Role) this.roleDao.getHibernateTemplate().load(Role.class, id) ;
		assertNotNull(roleTmp) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDao#getAllRole()}.
	 * 
	 * PRINCIPLE Create a tmp role, save it into the database. Then get all roles from the
	 * database with the method to test, and look if the size of the roles set got is >= 1. To
	 * finish delete this tmp role from the database.
	 */
	public void testGetAllRole () {
		// Rk: the setUp method is called here.

		// Save the role into the database.
		this.roleDao.getHibernateTemplate().saveOrUpdate(this.role) ;



		// Look if this role is also into the database and look if the size of the set is >= 1.
		List <Role> roles = this.roleDao.getAllRole() ;
		assertNotNull(roles) ;
		assertTrue(roles.size() >= 1) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDao#getRole()}.
	 * 
	 */
	public void testGetRole () {
		// Rk: the setUp method is called here.

		// Add prooperties to the role.
		this.role.setName(NAME) ;
		this.role.setDescription(DESCRIPTION) ;

		// Save the role into the database.
		this.roleDao.getHibernateTemplate().saveOrUpdate(this.role) ;
		String id = this.role.getId() ;

		

		// Test the method getActivity with an existing activity.
		Role roleTmp = this.roleDao.getRole(id) ;
		assertNotNull(roleTmp) ;
		assertEquals("Name", roleTmp.getName(), NAME) ;
		assertEquals("Description", roleTmp.getDescription(), DESCRIPTION) ;
		

		// Test the method getRole with an unexisting role.
		this.roleDao.getHibernateTemplate().delete(role) ;
		roleTmp = this.roleDao.getRole(id) ;
		assertNull(roleTmp) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDao#deleteRole()}.
	 * 
	 */
	public void testDeleteRole () {
		// Rk: the setUp method is called here.

		// Save the role into the database.
		this.roleDao.getHibernateTemplate().saveOrUpdate(this.role) ;
		String id = this.role.getId() ;


		// Test the method deleteRole with an role existing into the db.
		this.roleDao.deleteRole(this.role) ;


		// See if this.role is now absent in the db.
		Role roleTmp = (Role) this.roleDao.getHibernateTemplate().get(Role.class, id) ;
		assertNull(roleTmp) ;

		// Test the method deleteRole with an role unexisting into the db.
		// Normally here there are no exception thrown.
		this.roleDao.deleteRole(this.role) ;

		// Rk: the tearDown method is called here.
	}
}
