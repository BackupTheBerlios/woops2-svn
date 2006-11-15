package woops2.test.hibernate.element;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author deder
 *
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.hibernate.element") ;
		//$JUnit-BEGIN$
		suite.addTestSuite(ElementDaoTest.class) ;
		//$JUnit-END$
		return suite ;
	}

}
