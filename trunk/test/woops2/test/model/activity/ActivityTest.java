package woops2.test.model.activity;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;

import woops2.model.activity.Activity;

import junit.framework.TestCase;

public class ActivityTest extends TestCase {
	
		public static final String PREFIX = "prefix";
	
		public void testCreateActivity () {
			// Getback the application context from the spring configuration file
			Resource resource = new FileSystemResource("src/applicationContext.xml") ;
			XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
			// Getback the hibernateTemplate bean
			HibernateTemplate hibernateTemplate = (HibernateTemplate) xmlBeanFactory.getBean("hibernateTemplate") ;
	
			// Create empty Activity
			Activity activity = new Activity() ;
			activity.setPrefix(PREFIX);
			hibernateTemplate.saveOrUpdate(activity);
			final String id = activity.getId();
			hibernateTemplate.flush();
			hibernateTemplate.clear();
			
			Activity activityTmp =(Activity) hibernateTemplate.get(Activity.class, id);
			assertNotNull(activityTmp);
			assertEquals(activityTmp.getPrefix(), PREFIX);
			
		}

}
