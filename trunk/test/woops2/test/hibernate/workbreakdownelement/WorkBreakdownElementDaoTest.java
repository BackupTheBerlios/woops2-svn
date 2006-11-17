package woops2.test.hibernate.workbreakdownelement;

import java.util.Set;

import woops2.hibernate.workbreakdownelement.WorkBreakdownElementDao;
import woops2.model.breakdownelement.BreakdownElement;
import woops2.model.workbreakdownelement.WorkBreakdownElement;
import woops2.test.TestConfiguration;

/**
 * @author Sebastien
 * 
 * Unit test for WorkBreakdownElementDao TODO
 * 
 */
public class WorkBreakdownElementDaoTest extends TestConfiguration {

	private WorkBreakdownElementDao workBreakdownElementDao = null;

	private WorkBreakdownElement workBreakdownElement = null;

	public static final String NAME = "thisWBdE";

	public static final String DESCRIPTION = "wbde description";

	public static final String PREFIX = "prefix";

	public static final Boolean IS_OPTIONAL = true;

	public static final Boolean HAS_MULTIPLE_OCCURENCES = true;

	public static final Boolean IS_PLANNED = true;

	public static final Boolean IS_REPEATABLE = true;

	public static final Boolean IS_ONGOING = true;

	public static final Boolean IS_EVEN_DRIVEN = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the BreakdownElementDao Singleton for managing BreakdownElement
		// data
		this.workBreakdownElementDao = (WorkBreakdownElementDao) super
				.getBeanFactory().getBean("WorkBreakdownElementDao");

		// Create empty WorkBreakdownElement
		this.workBreakdownElement = new WorkBreakdownElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		// Delete the tmp breakdownElement from the database.
		try {
			this.workBreakdownElementDao.getHibernateTemplate().delete(
					this.workBreakdownElementDao);
		} catch (Exception exception) {
			// None.
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.workbreakdownElement.WorkBreakdownElementDao#saveOrUpdateWorkBreakdownElement(woops2.model.workbreakdownElement.workBreakdownElement)}.
	 * 
	 */
	public void testSaveOrUpdateWorkBreakdownElement() {
		// Rk: the setUp method is called here.

		// Save the workbreakdownElement with the method to test.
		this.workBreakdownElementDao
				.saveOrUpdateWorkBreakdownElement(this.workBreakdownElement);

		// Check the saving.
		String id = this.workBreakdownElement.getId();
		WorkBreakdownElement wbdeTmp = (WorkBreakdownElement) this.workBreakdownElementDao
				.getHibernateTemplate().load(WorkBreakdownElement.class, id);
		assertNotNull(wbdeTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.workbreakdownelement.WorkBreakdownElementDao#getAllWorkBreakdownElements()}.
	 * 
	 */
	public void testGetAllWorkBreakdownElements() {
		// Rk: the setUp method is called here.

		// Save the workBreakdownElement into the database.
		this.workBreakdownElementDao.getHibernateTemplate().saveOrUpdate(
				this.workBreakdownElement);

		// Look if this bde is also into the database and look if the size of
		// the set is >= 1.
		Set<WorkBreakdownElement> wbdes = this.workBreakdownElementDao
				.getAllWorkBreakdownElements();
		assertNotNull(wbdes);
		assertTrue(wbdes.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.workbreakdownelement.WorkBreakdownElementDao#getWorkBreakdownElement()}.
	 * 
	 */
	public void testGetWorkBreakdownElement() {
		// Rk: the setUp method is called here.

		// Add properties to the workBreakdownElement.
		this.workBreakdownElement.setName(NAME);
		this.workBreakdownElement.setDescription(DESCRIPTION);
		this.workBreakdownElement.setPrefix(PREFIX);
		this.workBreakdownElement
				.setHasMultipleOccurrences(HAS_MULTIPLE_OCCURENCES);
		this.workBreakdownElement.setIsOptional(IS_OPTIONAL);
		this.workBreakdownElement.setIsPlanned(IS_PLANNED);
		this.workBreakdownElement.setIsRepeatable(IS_REPEATABLE);
		this.workBreakdownElement.setIsOngoing(IS_ONGOING);
		this.workBreakdownElement.setIsEvenDriven(IS_EVEN_DRIVEN);

		// Save the workBreakdownElement into the database.
		this.workBreakdownElementDao.getHibernateTemplate().saveOrUpdate(
				this.workBreakdownElement);
		String id = this.workBreakdownElement.getId();

		// Test the method getWorkBreakdownElement with an existing
		// workBreakdownElement.
		WorkBreakdownElement wbdeTmp = this.workBreakdownElementDao
				.getWorkBreakdownElement(id);
		assertNotNull(wbdeTmp);
		assertEquals("Name", wbdeTmp.getName(), NAME);
		assertEquals("Description", wbdeTmp.getDescription(), DESCRIPTION);
		assertEquals("Prefix", wbdeTmp.getPrefix(), PREFIX);
		assertEquals("HasMultipleOccurences", wbdeTmp
				.getHasMultipleOccurrences(), HAS_MULTIPLE_OCCURENCES);
		assertEquals("IsOptional", wbdeTmp.getIsOptional(), IS_OPTIONAL);
		assertEquals("IsPlanned", wbdeTmp.getIsPlanned(), IS_PLANNED);
		assertEquals("IsRepeatable", wbdeTmp.getIsRepeatable(), IS_REPEATABLE);
		assertEquals("IsOngoing", wbdeTmp.getIsOngoing(), IS_ONGOING);
		assertEquals("IsEvenDriven", wbdeTmp.getIsEvenDriven(), IS_EVEN_DRIVEN);

		// Test the method getWorkBreakdownElement with an unexisting
		// workBreakdownElement.
		this.workBreakdownElementDao.getHibernateTemplate().delete(
				this.workBreakdownElement);
		wbdeTmp = this.workBreakdownElementDao.getWorkBreakdownElement(id);
		assertNull(wbdeTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.workbreakdownelement.WorkBreakdownElementDao#deleteWorkBreakdownElement()}.
	 * 
	 */
	public void testDeleteWorkBreakdownElement() {
		// Rk: the setUp method is called here.

		// Save the BreakdownElement into the database.
		this.workBreakdownElementDao.getHibernateTemplate().saveOrUpdate(
				this.workBreakdownElement);
		String id = this.workBreakdownElement.getId();

		// Test the method deleteBreakdownElement with an BreakdownElement
		// existing into the db.
		this.workBreakdownElementDao
				.deleteWorkBreakdownElement(this.workBreakdownElement);

		// See if this.breakdownElement is now absent in the db.
		BreakdownElement wbdeTmp = (BreakdownElement) this.workBreakdownElementDao
				.getHibernateTemplate().get(WorkBreakdownElement.class, id);
		assertNull(wbdeTmp);

		// Test the method deleteBreakdownElement with an BreakdownElement
		// unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.workBreakdownElementDao
				.deleteWorkBreakdownElement(this.workBreakdownElement);

		// Rk: the tearDown method is called here.
	}

}
