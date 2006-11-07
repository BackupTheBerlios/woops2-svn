
package woops2.test.hibernate.activity ;

import java.util.List;

import woops2.hibernate.activity.ActivityDao;
import woops2.model.activity.Activity;
import woops2.test.TestConfiguration;

/**
 * @author Mathieu BENOIT.
 *
 * This class represents ... TODO
 *
 */
public class ActivityDaoTest extends TestConfiguration {

	private ActivityDao activityDao;
	private Activity activity;
	
	public static final String PREFIX = "prefix";
	public static final Boolean IS_OPTIONAL = true;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp () throws Exception {
		super.setUp();
		
		// Get the ActivityDao Singleton for managing Activity data
		this.activityDao = (ActivityDao) super.xmlBeanFactory.getBean("ActivityDao") ;

		// Create empty Activity
		this.activity = new Activity() ;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown () throws Exception {
		super.tearDown();
		
		// Delete the tmp activity from the database.
		this.activityDao.getHibernateTemplate().delete(this.activity);
	}
	
	/**
	 * Test method for
	 * {@link woops2.hibernate.activity.ActivityDao#saveOrUpdateActivity(woops2.model.activity.Activity)}.
	 * 
	 * PRINCIPLE
	 * Create a tmp activity, save it into the database with the method to test.
	 * Then look for the database to check if this tmp activity exists.
	 * To finish delete this tmp activity from the database.
	 */
	public void testSaveOrUpdateActivity () {
		//Rk: the setUp method is called here.
		
		// Save the activity with the method to test.
		this.activityDao.saveOrUpdateActivity(this.activity);
		
		//Flush and clear the session.
		super.flushAndClear();
		
		//Check the saving.
		String id = activity.getId();
		Activity activityTmp = (Activity) this.activityDao.getHibernateTemplate().load(Activity.class, id) ;
		assertNotNull(activityTmp);
		
		//Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.activity.ActivityDao#getAllActivities()}.
	 * 
	 * PRINCIPLE
	 * Create a tmp activity, save it into the database.
	 * Then get all activities from the database with the method to test,
	 * and look if the size of the activities set got is >= 1.
	 * To finish delete this tmp activity from the database.
	 */
	public void testGetAllActivities () {
		//Rk: the setUp method is called here.
		
		// Save the activity into the database.
		this.activityDao.getHibernateTemplate().saveOrUpdate(this.activity) ;
		
		//Flush and clear the session.
		super.flushAndClear();
		
		// Look if this activity is also into the database and look if the size of the set is >= 1.
		List <Activity> activities = this.activityDao.getAllActivities(); 
		assertNotNull(activities) ;
		assertTrue(activities.size() >= 1) ;
		
		// Rk: the tearDown method is called here.
	}
}
