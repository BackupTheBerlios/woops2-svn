
package woops2.test.business.activity ;

import java.util.List ;

import org.springframework.beans.factory.xml.XmlBeanFactory ;
import org.springframework.core.io.FileSystemResource ;
import org.springframework.core.io.Resource ;
import org.springframework.orm.hibernate3.HibernateTemplate ;

import woops2.business.activity.ActivityManager ;
import woops2.model.activity.Activity ;
import junit.framework.TestCase ;

/**
 * @author Mathieu BENOIT.
 * 
 */
public class ActivityManagerTest extends TestCase {

	/**
	 * Test method for {@link woops2.business.activity.ActivityManager#getActivitiesList()}.
	 * 
	 * PRINCIPLE Create a tmp activity, save it into the database. Then get all activities from the
	 * database with the method to test, and look if the size of the activities set got is >= 1. To
	 * finish delete this tmp activity from the database.
	 */
	public void testGetActivitiesList () {
		// Getback the application context from the spring configuration file
		Resource resource = new FileSystemResource("src/applicationContext.xml") ;
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
		// Get the ActivityDao Singleton for managing Activity data
		ActivityManager activityManager = (ActivityManager) xmlBeanFactory.getBean("ActivityManager") ;

		// Create empty Activity
		Activity activity = new Activity() ;
		// Save it
		activityManager.getActivityDao().saveOrUpdateActivity(activity) ;
		
		//Flush and clear the session
		activityManager.getActivityDao().getHibernateTemplate().flush();
		activityManager.getActivityDao().getHibernateTemplate().clear();

		// Look if this activity is also into the database and look if the size of the set is >= 1.
		List <Activity> activities = activityManager.getActivitiesList() ; 
		assertNotNull(activities) ;
		assertTrue(activities.size() >= 1) ;

		// Delete the tmp activity from the database.
		activityManager.getActivityDao().deleteActivity(activity) ;
	}

	/**
	 * Test method for
	 * {@link woops2.business.activity.ActivityManager#saveActivity(woops2.model.activity.Activity)}.
	 * 
	 * PRINCIPLE Create a tmp activity, save it into the database with the method to test. Then look
	 * for the database to check if this tmp activity exists. To finish delete this tmp activity
	 * from the database.
	 */
	public void testSaveActivity () {
		// Getback the application context from the spring configuration file
		Resource resource = new FileSystemResource("src/applicationContext.xml") ;
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
		// Getback the hibernateTemplate bean
		HibernateTemplate hibernateTemplate = (HibernateTemplate) xmlBeanFactory.getBean("hibernateTemplate") ;
		// Get the ActivityDao Singleton for managing Activity data
		ActivityManager activityManager = (ActivityManager) xmlBeanFactory.getBean("ActivityManager") ;

		// Create empty Activity
		Activity activity = new Activity() ;
		// Save it
		activityManager.saveActivity(activity) ;
		
		//Flush and clear the session
		hibernateTemplate.flush();
		hibernateTemplate.clear();

		// Look if this activity is also into the database.
		Activity activityTmp = (Activity) hibernateTemplate.get(Activity.class, activity.getId()) ;
		assertNotNull(activityTmp) ;
		assertEquals(activity.getId(), activityTmp.getId()) ;
		assertEquals(activity.getPrefix(), activityTmp.getPrefix()) ;

		// Delete the tmp activity from the database.
		hibernateTemplate.delete(activityTmp) ;
	}

}
