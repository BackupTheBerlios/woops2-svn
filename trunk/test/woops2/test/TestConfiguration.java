package woops2.test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Mathieu BENOIT.
 *
 * This class represents the configuration for unit tests of this project (by using spring framework).
 * Each TestCase must use this class to use its static properties.
 *
 */
public class TestConfiguration {
	
	//Getback the application context from the spring configuration file
	private static Resource resource = new FileSystemResource("src/applicationContext.xml") ;
	public static XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
	
	
	/**
	 * TODO Method description
	 *
	 */
	public static void flushAndClear() {
		HibernateTemplate template = (HibernateTemplate) TestConfiguration.xmlBeanFactory.getBean("hibernateTemplate") ;
		template.flush();
		template.clear();
	}
}
