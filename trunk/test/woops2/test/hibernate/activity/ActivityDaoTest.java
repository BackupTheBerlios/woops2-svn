
package woops2.test.hibernate.activity ;

import java.util.List;

import junit.framework.TestCase ;

import org.springframework.beans.factory.xml.XmlBeanFactory ;
import org.springframework.core.io.FileSystemResource ;
import org.springframework.core.io.Resource ;
import org.springframework.orm.hibernate3.HibernateTemplate ;

import woops2.hibernate.activity.ActivityDao ;
import woops2.model.activity.Activity ;

/**
 * @author Mathieu BENOIT.
 * 
 */
public class ActivityDaoTest extends TestCase {

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
		// Getback the application context from the spring configuration file
		Resource resource = new FileSystemResource("src/applicationContext.xml") ;
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
		// Getback the hibernateTemplate bean
		HibernateTemplate hibernateTemplate = (HibernateTemplate) xmlBeanFactory.getBean("hibernateTemplate") ;
		// Get the ActivityDao Singleton for managing Activity data
		ActivityDao activityDao = (ActivityDao) xmlBeanFactory.getBean("ActivityDao") ;

		// Create empty Activity
		Activity activity = new Activity() ;
		activityDao.saveOrUpdateActivity(activity);
		
		//Flush and clear the session
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		final String id = activity.getId();
		// Save it
		Activity activityTmp = activityDao.getActivity(id);
		assertNotNull(activityTmp);
		
		//Delete the tmp activity from the database.
		activityDao.deleteActivity(activity);
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
		//Getback the application context from the spring configuration file
		Resource resource = new FileSystemResource("src/applicationContext.xml") ;
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
		// Getback the hibernateTemplate bean
		HibernateTemplate hibernateTemplate = (HibernateTemplate) xmlBeanFactory.getBean("hibernateTemplate") ;
		// Get the ActivityDao Singleton for managing Activity data
		ActivityDao activityDao = (ActivityDao) xmlBeanFactory.getBean("ActivityDao") ;

		// Create empty Activity
		Activity activity = new Activity() ;
		// Save it
		hibernateTemplate.saveOrUpdate(activity) ;
		
		//Flush and clear the session
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		
		// Look if this activity is also into the database and look if the size of the set is >= 1.
		List <Activity> activities = activityDao.getAllActivities(); 
		assertNotNull(activities) ;
		assertTrue(activities.size() >= 1) ;
		
		//Delete the tmp activity from the database.
		hibernateTemplate.delete(activity);
	}

}
