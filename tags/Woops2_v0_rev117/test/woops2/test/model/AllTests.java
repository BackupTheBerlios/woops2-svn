
package woops2.test.model ;

import junit.framework.Test ;
import junit.framework.TestSuite ;

/**
 * @author Mathieu BENOIT.
 * 
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.model") ;
		// $JUnit-BEGIN$
		//suite.addTest(woops2.test.model.activity.AllTests.suite()) ;
		// $JUnit-END$
		return suite ;
	}

}
