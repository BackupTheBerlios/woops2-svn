package woops2.test.hibernate.breakdownelement;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author deder
 *
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.hibernate.breakdownelement") ;
		//$JUnit-BEGIN$
		suite.addTestSuite(BreakdownElementDaoTest.class) ;
		//$JUnit-END$
		return suite ;
	}

}
