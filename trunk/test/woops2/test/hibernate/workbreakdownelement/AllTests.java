package woops2.test.hibernate.workbreakdownelement;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Sebastien
 *
 * This class represents ... TODO
 *
 */
public class AllTests {

	public static Test suite () {
		TestSuite suite = new TestSuite("Test for woops2.test.hibernate.workbreakdownelement") ;
		//$JUnit-BEGIN$
		suite.addTestSuite(WorkBreakdownElementDaoTest.class) ;
		//$JUnit-END$
		return suite ;
	}

}