package woops2.test.hibernate.task;

import java.util.Set;

import woops2.hibernate.task.TaskDescriptorDao;
import woops2.model.task.TaskDescriptor;
import woops2.test.TestConfiguration;

/**
 * Unit test for TaskDescriptorDao
 * 
 * @author eperico
 * 
 */
public class TaskDescriptorDaoTest extends TestConfiguration {

	private TaskDescriptorDao taskDescriptorDao = null;

	private TaskDescriptor taskDescriptor = null;

	/**
	 * attributes from Element
	 */
	public static final String ID = "thisId";

	public static final String NAME = "thisTaskDescriptor";

	public static final String DESCRIPTION = "taskDescriptor description";

	/**
	 * attributes from BreakdownElement
	 */
	public static final String PREFIX = "prefix";

	public static final Boolean IS_PLANNED = true;

	public static final Boolean HAS_MULTIPLE_OCCURENCES = true;

	public static final Boolean IS_OPTIONAL = true;

	/**
	 * attributes from WorkBreakdownElement
	 */
	public static final Boolean IS_REPEATABLE = true;

	public static final Boolean IS_ON_GOING = true;

	public static final Boolean IS_EVEN_DRIVEN = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the TaskDescriptorDao Singleton for managing TaskDescriptor data
		this.taskDescriptorDao = (TaskDescriptorDao) super.getBeanFactory()
				.getBean("TaskDescriptorDao");

		// Create empty TaskDescriptor
		this.taskDescriptor = new TaskDescriptor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		// Delete the tmp taskDescriptor from the database.
		try {
			this.taskDescriptorDao.getHibernateTemplate().delete(
					this.taskDescriptor);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDescriptorDao#saveOrUpdateTaskDescriptor(woops2.model.task.TaskDescriptor)}.
	 * 
	 * PRINCIPLE Create a tmp taskDescriptor, save it into the database with the
	 * method to test. Then look for the database to check if this tmp
	 * taskDescriptor exists. To finish delete this tmp taskDescriptor from the
	 * database.
	 */
	public void testSaveOrUpdateTaskDescriptor() {
		// Rk: the setUp method is called here.

		// Save the taskDescriptor with the method to test.
		this.taskDescriptorDao.saveOrUpdateTaskDescriptor(this.taskDescriptor);

		// Check the saving.
		String id = taskDescriptor.getId();
		TaskDescriptor taskDescriptorTmp = (TaskDescriptor) this.taskDescriptorDao
				.getHibernateTemplate().load(TaskDescriptor.class, id);
		assertNotNull(taskDescriptorTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDescriptorDao#getAllTaskDescriptors()}.
	 * 
	 * PRINCIPLE Create a tmp taskDescriptor, save it into the database. Then
	 * get all activities from the database with the method to test, and look if
	 * the size of the activities set got is >= 1. To finish delete this tmp
	 * taskDescriptor from the database.
	 */
	public void testGetAllTaskDescriptors() {
		// Rk: the setUp method is called here.

		// Save the taskDescriptor into the database.
		this.taskDescriptorDao.getHibernateTemplate().saveOrUpdate(
				this.taskDescriptor);

		// Look if this taskDescriptor is also into the database and look if the
		// size of the set is >= 1.
		Set<TaskDescriptor> taskDescriptors = this.taskDescriptorDao
				.getAllTaskDescriptor();
		assertNotNull(taskDescriptors);
		assertTrue(taskDescriptors.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDescriptorDao#getTaskDescriptor()}.
	 * 
	 */
	public void testGetTaskDescriptor() {
		// Rk: the setUp method is called here.

		// Add prooperties to the taskDescriptor.
		this.taskDescriptor.setName(NAME);
		this.taskDescriptor.setDescription(DESCRIPTION);

		this.taskDescriptor.setPrefix(PREFIX);
		this.taskDescriptor.setIsPlanned(IS_PLANNED);
		this.taskDescriptor.setHasMultipleOccurrences(HAS_MULTIPLE_OCCURENCES);
		this.taskDescriptor.setIsOptional(IS_OPTIONAL);

		this.taskDescriptor.setIsRepeatable(IS_REPEATABLE);
		this.taskDescriptor.setIsOngoing(IS_ON_GOING);
		this.taskDescriptor.setIsEvenDriven(IS_EVEN_DRIVEN);

		// Save the taskDescriptor into the database.
		this.taskDescriptorDao.getHibernateTemplate().saveOrUpdate(
				this.taskDescriptor);
		String id = this.taskDescriptor.getId();

		// Test the method getTaskDescriptor with an existing taskDescriptor.
		TaskDescriptor taskDescriptorTmp = this.taskDescriptorDao
				.getTaskDescriptor(id);
		assertNotNull(taskDescriptorTmp);
		assertEquals("Name", taskDescriptorTmp.getName(), NAME);
		assertEquals("Description", taskDescriptorTmp.getDescription(),
				DESCRIPTION);
		assertEquals("Prefix", taskDescriptorTmp.getPrefix(), PREFIX);
		assertEquals("IsPlanned", taskDescriptorTmp.getIsPlanned(), IS_PLANNED);
		assertEquals("HasMultipleOccurences", taskDescriptorTmp
				.getHasMultipleOccurrences(), HAS_MULTIPLE_OCCURENCES);
		assertEquals("IsOptional", taskDescriptorTmp.getIsOptional(),
				IS_OPTIONAL);
		assertEquals("IsRepeatable", taskDescriptorTmp.getIsRepeatable(),
				IS_REPEATABLE);
		assertEquals("IsOnGoing", taskDescriptorTmp.getIsOngoing(), IS_ON_GOING);
		assertEquals("IsEvenDriven", taskDescriptorTmp.getIsEvenDriven(),
				IS_EVEN_DRIVEN);

		// Test the method getTaskDescriptor with an unexisting taskDescriptor.
		this.taskDescriptorDao.getHibernateTemplate().delete(taskDescriptor);
		taskDescriptorTmp = this.taskDescriptorDao.getTaskDescriptor(id);
		assertNull(taskDescriptorTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDescriptorDao#deleteTaskDescriptor()}.
	 * 
	 */
	public void testDeleteTaskDescriptor() {
		// Rk: the setUp method is called here.

		// Save the taskDescriptor into the database.
		this.taskDescriptorDao.getHibernateTemplate().saveOrUpdate(
				this.taskDescriptor);
		String id = this.taskDescriptor.getId();

		// Test the method deleteTaskDescriptor with an acitivity existing into
		// the db.
		this.taskDescriptorDao.deleteTaskDescriptor(this.taskDescriptor);

		// See if this.taskDescriptor is now absent in the db.
		TaskDescriptor taskDescriptorTmp = (TaskDescriptor) this.taskDescriptorDao
				.getHibernateTemplate().get(TaskDescriptor.class, id);
		assertNull(taskDescriptorTmp);

		// Test the method deleteTaskDescriptor with a taskDescriptor unexisting
		// into the db.
		// FIXME Normally here there are no exception thrown.
		this.taskDescriptorDao.deleteTaskDescriptor(this.taskDescriptor);

		// Rk: the tearDown method is called here.
	}
}
