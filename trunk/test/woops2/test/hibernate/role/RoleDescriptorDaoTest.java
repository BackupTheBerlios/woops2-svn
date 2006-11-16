
package woops2.test.hibernate.role ;

import java.util.List ;

import woops2.hibernate.role.RoleDescriptorDao ;
import woops2.model.role.RoleDescriptor ;
import woops2.test.TestConfiguration ;

/**
 * Unit test for RoleDescriptorDao
 * 
 * @author Soosuske.
 */
public class RoleDescriptorDaoTest extends TestConfiguration {

	private RoleDescriptorDao roleDescriptorDao = null ;

	private RoleDescriptor roleDescriptor = null ;

	public static final String NAME = "thisRoleDescriptor" ;

	public static final String DESCRIPTION = "roleDescriptor description" ;

	public static final String PREFIX = "prefix" ;

	public static final Boolean IS_OPTIONAL = true ;

	public static final Boolean HAS_MULTIPLE_OCCURENCES = true ;

	public static final Boolean IS_PLANNED = true ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp () throws Exception {
		super.setUp() ;

		// Get the RoleDescriptorDao Singleton for managing RoleDescriptor data
		this.roleDescriptorDao = (RoleDescriptorDao) super.getBeanFactory().getBean("RoleDescriptorDao") ;

		// Create empty roleDescriptor
		this.roleDescriptor = new RoleDescriptor() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown () throws Exception {
		super.tearDown() ;

		// Delete the tmp roleDescriptor from the database.
		try {
			this.roleDescriptorDao.getHibernateTemplate().delete(this.roleDescriptor) ;
		}
		catch (Exception exception) {
			// None.
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.role.RoleDescriptorDao#saveOrUpdateRoleDescriptor(woops2.model.role.RoleDescriptor)}.
	 * 
	 * PRINCIPLE Create a tmp roleDescriptor, save it into the database with the method to test. Then look
	 * for the database to check if this tmp roleDescriptor exists. To finish delete this tmp roleDescriptor
	 * from the database.
	 */
	public void testSaveOrUpdateRoleDescriptor () {
		// Rk: the setUp method is called here.

		// Save the roleDescriptor with the method to test.
		this.roleDescriptorDao.saveOrUpdateRoleDescriptor(this.roleDescriptor) ;


		// Check the saving.
		String id = roleDescriptor.getId() ;
		RoleDescriptor roleDescriptorTmp = (RoleDescriptor) this.roleDescriptorDao.getHibernateTemplate().load(RoleDescriptor.class, id) ;
		assertNotNull(roleDescriptorTmp) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDescriptorDao#getAllRoleDescriptor()}.
	 * 
	 * PRINCIPLE Create a tmp roleDescriptor, save it into the database. Then get all roleDescriptor from the
	 * database with the method to test, and look if the size of the roleDescriptor set got is >= 1. To
	 * finish delete this tmp roleDescriptor from the database.
	 */
	public void testGetAllRoleDescriptor () {
		// Rk: the setUp method is called here.

		// Save the roleDescriptor into the database.
		this.roleDescriptorDao.getHibernateTemplate().saveOrUpdate(this.roleDescriptor) ;

		// Look if this roleDescriptor is also into the database and look if the size of the set is >= 1.
		List <RoleDescriptor> roleDescriptors = this.roleDescriptorDao.getAllRoleDescriptor() ;
		assertNotNull(roleDescriptors) ;
		assertTrue(roleDescriptors.size() >= 1) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDescriptorDao#getRoleDescriptor()}.
	 * 
	 */
	public void testGetRoleDescriptor () {
		// Rk: the setUp method is called here.

		// Add prooperties to the roleDescriptor.
		this.roleDescriptor.setName(NAME) ;
		this.roleDescriptor.setDescription(DESCRIPTION) ;
		this.roleDescriptor.setPrefix(PREFIX) ;
		this.roleDescriptor.setHasMultipleOccurrences(HAS_MULTIPLE_OCCURENCES) ;
		this.roleDescriptor.setIsOptional(IS_OPTIONAL) ;
		this.roleDescriptor.setIsPlanned(IS_PLANNED) ;

		// Save the roleDescriptor into the database.
		this.roleDescriptorDao.getHibernateTemplate().saveOrUpdate(this.roleDescriptor) ;
		String id = this.roleDescriptor.getId() ;


		// Test the method getRoleDescriptor with an existing roleDescriptor.
		RoleDescriptor roleDescriptorTmp = this.roleDescriptorDao.getRoleDescriptor(id) ;
		assertNotNull(roleDescriptorTmp) ;
		assertEquals("Name", roleDescriptorTmp.getName(), NAME) ;
		assertEquals("Description", roleDescriptorTmp.getDescription(), DESCRIPTION) ;
		assertEquals("Prefix", roleDescriptorTmp.getPrefix(), PREFIX) ;
		assertEquals("HasMultipleOccurences", roleDescriptorTmp.getHasMultipleOccurrences(), HAS_MULTIPLE_OCCURENCES) ;
		assertEquals("IsOptional", roleDescriptorTmp.getIsOptional(), IS_OPTIONAL) ;
		assertEquals("IsPlanned", roleDescriptorTmp.getIsPlanned(), IS_PLANNED) ;

		// Test the method getRoleDescriptor with an unexisting roleDescriptor.
		this.roleDescriptorDao.getHibernateTemplate().delete(roleDescriptor) ;
		roleDescriptorTmp = this.roleDescriptorDao.getRoleDescriptor(id) ;
		assertNull(roleDescriptorTmp) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.role.RoleDescriptorDao#deleteRoleDescriptor()}.
	 * 
	 */
	public void testDeleteRoleDescriptor () {
		// Rk: the setUp method is called here.

		// Save the roleDescriptor into the database.
		this.roleDescriptorDao.getHibernateTemplate().saveOrUpdate(this.roleDescriptor) ;
		String id = this.roleDescriptor.getId() ;


		// Test the method deleteRoleDescriptor with an roleDescriptor existing into the db.
		this.roleDescriptorDao.deleteRoleDescriptor(this.roleDescriptor) ;


		// See if this.roleDescriptor is now absent in the db.
		RoleDescriptor roleDescriptorTmp = (RoleDescriptor) this.roleDescriptorDao.getHibernateTemplate().get(RoleDescriptor.class, id) ;
		assertNull(roleDescriptorTmp) ;

		// Test the method deleteRoleDescriptor with an roleDescriptor unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.roleDescriptorDao.deleteRoleDescriptor(this.roleDescriptor) ;

		// Rk: the tearDown method is called here.
	}
}
