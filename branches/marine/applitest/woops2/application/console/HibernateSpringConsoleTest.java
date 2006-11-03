package woops2.application.console;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import woops2.hibernate.activity.ActivityDao;
import woops2.model.activity.Activity;

/**
 * Application for testing hibernate and spring configuration with small model
 * @author garwind
 */
public class HibernateSpringConsoleTest {

	
	public static void main(String[] args) {
		// Getback the application context from the spring configuration file
		Resource res = new FileSystemResource("src/applicationContext.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		// Show what is in the factory
		System.out.println("factory => "+factory);
		// Getback the hibernateTemplate bean
		org.springframework.orm.hibernate3.HibernateTemplate hibTempl = (org.springframework.orm.hibernate3.HibernateTemplate) factory.getBean("hibernateTemplate");
		System.out.println("HibTemplate => "+hibTempl);
		// Get the ActivityDao Singleton for managing Activity data
		ActivityDao dao = (ActivityDao) factory.getBean("ActivityDao");
		// Create empty Activity
		Activity a = new Activity();
		// Save it
		dao.saveOrUpdateActivity(a);
	}

}
