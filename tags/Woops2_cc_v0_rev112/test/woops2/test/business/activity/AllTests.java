
package woops2.test.business.activity ;

import junit.framework.Test ;
import junit.framework.TestSuite ;

/**
 * @author Mathieu BENOIT.
 * 
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.business.activity") ;
		// $JUnit-BEGIN$
		suite.addTestSuite(ActivityManagerTest.class) ;
		// $JUnit-END$
		return suite ;
	}

}
