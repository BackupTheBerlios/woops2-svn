package woops2.test.hibernate.activity;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Mathieu BENOIT.
 *
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.hibernate.activity") ;
		//$JUnit-BEGIN$
		suite.addTestSuite(ActivityDaoTest.class) ;
		//$JUnit-END$
		return suite ;
	}

}
