
package woops2.test.hibernate ;

import junit.framework.Test ;
import junit.framework.TestSuite ;

/**
 * @author Mathieu BENOIT.
 * 
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.hibernate") ;
		// $JUnit-BEGIN$
		suite.addTest(woops2.test.hibernate.activity.AllTests.suite()) ;
		// $JUnit-END$
		return suite ;
	}

}
