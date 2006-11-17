package woops2.test.hibernate.task;

import java.util.Set;

import woops2.hibernate.task.SectionDao;
import woops2.model.task.Section;
import woops2.model.task.Task;
import woops2.test.TestConfiguration;

/**
 * TODO finir commentaires
 * 
 * @author garwind
 * 
 */
public class SectionDaoTest extends TestConfiguration {
	private SectionDao sectionDao = null;

	private Section section = null;

	/**
	 * attributes from Element
	 */
	public static final String ID = "thisId";

	public static final String NAME = "thisSection";

	public static final String DESCRIPTION = "section";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the TaskDao Singleton for managing Task data
		this.sectionDao = (SectionDao) super.getBeanFactory().getBean(
				"SectionDao");

		// Create empty Task
		this.section = new Section();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		// Delete the tmp task from the database.
		try {
			this.sectionDao.getHibernateTemplate().delete(this.section);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void testSaveOrUpdateTask() {
		// Rk: the setUp method is called here.

		// Save the task with the method to test.
		this.sectionDao.saveOrUpdateSection(this.section);

		// Check the saving.
		String id = section.getId();
		Task taskTmp = (Task) this.sectionDao.getHibernateTemplate().load(
				Task.class, id);
		assertNotNull(taskTmp);

		// Rk: the tearDown method is called here.
	}

	public void testGetAllTasks() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.sectionDao.getHibernateTemplate().saveOrUpdate(this.section);

		// Look if this task is also into the database and look if the size of
		// the set is >= 1.
		Set<Section> sections = this.sectionDao.getAllSection();
		assertNotNull(sections);
		assertTrue(sections.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	public void testGetTask() {
		// Rk: the setUp method is called here.

		// Add prooperties to the task.
		this.section.setName(NAME);
		this.section.setDescription(DESCRIPTION);

		// Save the task into the database.
		this.sectionDao.getHibernateTemplate().saveOrUpdate(this.section);
		String id = this.section.getId();

		// Test the method getTask with an existing task.
		Section sectionTmp = this.sectionDao.getSection(id);
		assertNotNull(sectionTmp);
		assertEquals("Name", sectionTmp.getName(), NAME);
		assertEquals("Description", sectionTmp.getDescription(), DESCRIPTION);

		// Test the method getTask with an unexisting task.
		this.sectionDao.getHibernateTemplate().delete(section);
		sectionTmp = this.sectionDao.getSection(id);
		assertNull(sectionTmp);

		// Rk: the tearDown method is called here.
	}

	public void testDeleteTask() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.sectionDao.getHibernateTemplate().saveOrUpdate(this.section);
		String id = this.section.getId();

		// Test the method deleteTask with an acitivity existing into the db.
		this.sectionDao.deleteSection(this.section);

		// See if this.task is now absent in the db.
		Task taskTmp = (Task) this.sectionDao.getHibernateTemplate().get(
				Task.class, id);
		assertNull(taskTmp);

		// Test the method deleteTask with a task unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.sectionDao.deleteSection(this.section);

		// Rk: the tearDown method is called here.
	}
}
