
package woops2.test.model.activity ;

import junit.framework.TestCase ;

import org.springframework.orm.hibernate3.HibernateTemplate ;

import woops2.model.activity.Activity ;
import woops2.test.TestConfiguration ;

/**
 * @author Mathieu BENOIT.
 *
 * This class represents ... TODO
 *
 */
public class ActivityTest extends TestCase {

	private HibernateTemplate hibernateTemplate ;
	private Activity activity ;

	public static final String PREFIX = "prefix" ;
	public static final Boolean IS_OPTIONAL = true ;
	public static final Boolean HAS_MULTIPLE_OCCURENCES = true ;
	public static final Boolean IS_EVEN_DRIVEN = true ;
	public static final Boolean IS_ON_GOING = true ;
	public static final Boolean IS_PLANNED = true ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp () throws Exception {
		super.setUp() ;

		// Getback the hibernateTemplate bean.
		this.hibernateTemplate = (HibernateTemplate) TestConfiguration.xmlBeanFactory.getBean("hibernateTemplate") ;

		// Create an empty Activity.
		this.activity = new Activity() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown () throws Exception {
		super.tearDown() ;

		// Delete the tmp activity from the database.
		this.hibernateTemplate.delete(this.activity) ;
	}

	public void testCreateActivity () {
		// Rk: the setUp method is called here.

		this.activity.setPrefix(PREFIX) ;
		this.activity.setHasMultipleOccurrences(HAS_MULTIPLE_OCCURENCES) ;
		this.activity.setIsEvenDriven(IS_EVEN_DRIVEN) ;
		this.activity.setIsOngoing(IS_ON_GOING) ;
		this.activity.setIsOptional(IS_OPTIONAL) ;
		this.activity.setIsPlanned(IS_PLANNED) ;

		this.hibernateTemplate.saveOrUpdate(this.activity) ;
		String id = this.activity.getId() ;

		// Flush and clear the session.
		TestConfiguration.flushAndClear() ;

		Activity activityTmp = (Activity) this.hibernateTemplate.get(Activity.class, id) ;
		assertNotNull(activityTmp) ;
		assertEquals("Prefix", activityTmp.getPrefix(), PREFIX) ;
		assertEquals("HasMultipleOccurences", activityTmp.getHasMultipleOccurrences(), HAS_MULTIPLE_OCCURENCES) ;
		assertEquals("IsEvenDriven", activityTmp.getIsEvenDriven(), IS_EVEN_DRIVEN) ;
		assertEquals("IsOnGoing", activityTmp.getIsOngoing(), IS_ON_GOING) ;
		assertEquals("IsOptional", activityTmp.getIsOptional(), IS_OPTIONAL) ;
		assertEquals("IsPlanned", activityTmp.getIsPlanned(), IS_PLANNED) ;

		// Rk: the tearDown method is called here.
	}

}
