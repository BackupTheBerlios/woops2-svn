package woops2.test.hibernate.element;

import java.util.Set;

import woops2.hibernate.element.ElementDao;
import woops2.model.element.Element;
import woops2.test.TestConfiguration;

/**
 * Unit test for ElementDao
 * 
 * @author deder
 */
public class ElementDaoTest extends TestConfiguration {

	private ElementDao elementDao = null;

	private Element element = null;

	public static final String NAME = "thisElt";

	public static final String DESCRIPTION = "elt description";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the ElementDao Singleton for managing Element data
		this.elementDao = (ElementDao) super.getBeanFactory().getBean(
				"ElementDao");

		// Create empty Activity
		this.element = new Element();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		// Delete the tmp element from the database.
		try {
			this.elementDao.getHibernateTemplate().delete(this.element);
		} catch (Exception exception) {
			// None.
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.element.ElementDao#saveOrUpdateActivity(woops2.model.element.Element)}.
	 * 
	 */
	public void testSaveOrUpdateElement() {
		// Rk: the setUp method is called here.

		// Save the element with the method to test.
		this.elementDao.saveOrUpdateElement(this.element);

		// Check the saving.
		String id = this.element.getId();
		Element elementTmp = (Element) this.elementDao.getHibernateTemplate()
				.load(Element.class, id);
		assertNotNull(elementTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.element.ElementDao#getAllElements()}.
	 * 
	 */
	public void testgetAllElements() {
		// Rk: the setUp method is called here.

		// Save the element into the database.
		this.elementDao.getHibernateTemplate().saveOrUpdate(this.element);

		// Look if this element is also into the database and look if the size
		// of the set is >= 1.
		Set<Element> elements = this.elementDao.getAllElements();
		assertNotNull(elements);
		assertTrue(elements.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.element.ElementDao#getElement()}.
	 * 
	 */
	public void testGetElement() {
		// Rk: the setUp method is called here.

		// Add properties to the element.
		this.element.setName(NAME);
		this.element.setDescription(DESCRIPTION);

		// Save the element into the database.
		this.elementDao.getHibernateTemplate().saveOrUpdate(this.element);
		String id = this.element.getId();

		// Test the method getElement with an existing element.
		Element elementTmp = this.elementDao.getElement(id);
		assertNotNull(elementTmp);
		assertEquals("Name", elementTmp.getName(), NAME);
		assertEquals("Description", elementTmp.getDescription(), DESCRIPTION);

		// Test the method getElement with an unexisting element.
		this.elementDao.getHibernateTemplate().delete(this.element);
		elementTmp = this.elementDao.getElement(id);
		assertNull(elementTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.element.ElementDao#deleteElement()}.
	 * 
	 */
	public void testDeleteElement() {
		// Rk: the setUp method is called here.

		// Save the element into the database.
		this.elementDao.getHibernateTemplate().saveOrUpdate(this.element);
		String id = this.element.getId();

		// Test the method deleteElement with an element existing into the db.
		this.elementDao.deleteElement(this.element);

		// See if this.element is now absent in the db.
		Element elementTmp = (Element) this.elementDao.getHibernateTemplate()
				.get(Element.class, id);
		assertNull(elementTmp);

		// Test the method deleteElement with an element unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.elementDao.deleteElement(this.element);

		// Rk: the tearDown method is called here.
	}
}
