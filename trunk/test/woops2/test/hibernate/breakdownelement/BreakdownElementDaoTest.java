
package woops2.test.hibernate.breakdownelement ;

import java.util.List;

import woops2.hibernate.breakdownelement.BreakdownElementDao;
import woops2.model.activity.Activity;
import woops2.model.breakdownelement.BreakdownElement;
import woops2.test.TestConfiguration;

/**
 * Unit test for BreakdownElementDao
 * 
 * @author deder
 */
public class BreakdownElementDaoTest extends TestConfiguration {

	private BreakdownElementDao breakdownElementDao = null ;

	private BreakdownElement breakdownElement = null ;

	public static final String NAME = "thisBde" ;

	public static final String DESCRIPTION = "bde description" ;

	public static final String PREFIX = "prefix" ;

	public static final Boolean IS_OPTIONAL = true ;

	public static final Boolean HAS_MULTIPLE_OCCURENCES = true ;

	public static final Boolean IS_EVEN_DRIVEN = true ;

	public static final Boolean IS_ON_GOING = true ;

	public static final Boolean IS_PLANNED = true ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp () throws Exception {
		super.setUp() ;

		// Get the BreakdownElementDao Singleton for managing BreakdownElement data
		this.breakdownElementDao = (BreakdownElementDao) super.getBeanFactory().getBean("BreakdownElementDao") ;

		// Create empty BreakdownElement
		this.breakdownElement = new BreakdownElement() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown () throws Exception {
		super.tearDown() ;

		// Delete the tmp breakdownElement from the database.
		try {
			this.breakdownElementDao.getHibernateTemplate().delete(this.breakdownElement) ;
		}
		catch (Exception exception) {
			// None.
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.breakdownElement.BreakdownElementDao#saveOrUpdateBreakdownElement(woops2.model.breakdownElement.breakdownElement)}.
	 * 
	 */
	public void testSaveOrUpdateBreakdownElement () {
		// Rk: the setUp method is called here.

		// Save the activity with the method to test.
		this.breakdownElementDao.saveOrUpdateBreakdownElement(this.breakdownElement) ;

		// Check the saving.
		String id = this.breakdownElement.getId() ;
		Activity activityTmp = (Activity) this.breakdownElementDao.getHibernateTemplate().load(Activity.class, id) ;
		assertNotNull(activityTmp) ;

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.activity.ActivityDao#getAllActivities()}.
	 * 
	 */
	public void testGetAllActivities () {
		// Rk: the setUp method is called here.

		/*// Save the activity into the database.
		this.activityDao.getHibernateTemplate().saveOrUpdate(this.activity) ;

		// Look if this activity is also into the database and look if the size of the set is >= 1.
		List <Activity> activities = this.activityDao.getAllActivities() ;
		assertNotNull(activities) ;
		assertTrue(activities.size() >= 1) ;*/

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.activity.ActivityDao#getActivity()}.
	 * 
	 */
	public void testGetActivity () {
		// Rk: the setUp method is called here.

		/*// Add properties to the activity.
		this.activity.setName(NAME) ;
		this.activity.setDescription(DESCRIPTION) ;
		this.activity.setPrefix(PREFIX) ;
		this.activity.setHasMultipleOccurrences(HAS_MULTIPLE_OCCURENCES) ;
		this.activity.setIsEvenDriven(IS_EVEN_DRIVEN) ;
		this.activity.setIsOngoing(IS_ON_GOING) ;
		this.activity.setIsOptional(IS_OPTIONAL) ;
		this.activity.setIsPlanned(IS_PLANNED) ;

		// Save the activity into the database.
		this.activityDao.getHibernateTemplate().saveOrUpdate(this.activity) ;
		String id = this.activity.getId() ;

		// Test the method getActivity with an existing activity.
		Activity activityTmp = this.activityDao.getActivity(id) ;
		assertNotNull(activityTmp) ;
		assertEquals("Name", activityTmp.getName(), NAME) ;
		assertEquals("Description", activityTmp.getDescription(), DESCRIPTION) ;
		assertEquals("Prefix", activityTmp.getPrefix(), PREFIX) ;
		assertEquals("HasMultipleOccurences", activityTmp.getHasMultipleOccurrences(), HAS_MULTIPLE_OCCURENCES) ;
		assertEquals("IsEvenDriven", activityTmp.getIsEvenDriven(), IS_EVEN_DRIVEN) ;
		assertEquals("IsOnGoing", activityTmp.getIsOngoing(), IS_ON_GOING) ;
		assertEquals("IsOptional", activityTmp.getIsOptional(), IS_OPTIONAL) ;
		assertEquals("IsPlanned", activityTmp.getIsPlanned(), IS_PLANNED) ;

		// Test the method getActivity with an unexisting activity.
		this.activityDao.getHibernateTemplate().delete(this.activity) ;
		activityTmp = this.activityDao.getActivity(id) ;
		assertNull(activityTmp) ;*/

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.activity.ActivityDao#deleteActivity()}.
	 * 
	 */
	public void testDeleteActivity () {
		// Rk: the setUp method is called here.

		/*// Save the activity into the database.
		this.activityDao.getHibernateTemplate().saveOrUpdate(this.activity) ;
		String id = this.activity.getId() ;

		// Test the method deleteActivity with an activity existing into the db.
		this.activityDao.deleteActivity(this.activity) ;

		// See if this.activity is now absent in the db.
		Activity activityTmp = (Activity) this.activityDao.getHibernateTemplate().get(Activity.class, id) ;
		assertNull(activityTmp) ;

		// Test the method deleteActivity with an activity unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.activityDao.deleteActivity(this.activity) ;*/

		// Rk: the tearDown method is called here.
	}
}
