package woops2.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Mathieu BENOIT.
 *
 * This class represents the configuration for unit tests of this project (by using spring framework).
 * Each TestCase must extend this class to use its protected properties.
 *
 */
public class TestConfiguration extends TestCase{
	
	//Getback the application context from the spring configuration file
	private  Resource resource = new FileSystemResource("src/applicationContext.xml") ;
	protected  XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;
	
	
	protected void flushAndClear() {
		HibernateTemplate template = (HibernateTemplate) this.xmlBeanFactory.getBean("hibernateTemplate") ;
		template.flush();
		template.clear();
	}
}
