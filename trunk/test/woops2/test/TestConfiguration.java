
package woops2.test ;

import junit.framework.TestCase ;

import org.springframework.beans.factory.xml.XmlBeanFactory ;
import org.springframework.core.io.FileSystemResource ;
import org.springframework.core.io.Resource ;

/**
 * @author deder
 * 
 * This class represents the configuration for unit tests of this project (by using spring
 * framework). Each TestCase must extend this class to use its protected properties.
 * 
 */
public class TestConfiguration extends TestCase {

	// Getback the application context from the spring configuration file
	private Resource resource = new FileSystemResource("src/applicationContext.xml") ;

	private XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource) ;

	public XmlBeanFactory getBeanFactory () {
		return this.xmlBeanFactory ;
	}

}
