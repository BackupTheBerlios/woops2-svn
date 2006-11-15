
package woops2.test ;

import junit.framework.Test ;
import junit.framework.TestSuite ;

/**
 * @author deder
 * 
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("JUnit Tests for Woops2 project") ;
		// $JUnit-BEGIN$
		suite.addTest(woops2.test.business.AllTests.suite()) ;
		suite.addTest(woops2.test.hibernate.AllTests.suite()) ;
		suite.addTest(woops2.test.model.AllTests.suite()) ;
		// $JUnit-END$
		return suite ;
	}

}
