package woops2.test.hibernate.task;

import java.util.Set;

import woops2.hibernate.task.TaskDefinitionDao;
import woops2.model.task.TaskDefinition;
import woops2.test.TestConfiguration;

/**
 * Unit test for TaskDefinitionDao
 * 
 * @author eperico
 * 
 */
public class TaskDefinitionDaoTest extends TestConfiguration {
	private TaskDefinitionDao taskDefinitionDao = null;

	private TaskDefinition taskDefinition = null;

	/**
	 * attributes from Element
	 */
	public static final String ID = "thisId";

	public static final String NAME = "thisTask";

	public static final String DESCRIPTION = "taskDefinition description";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the TaskDefinitionDao Singleton for managing TaskDefinition data
		this.taskDefinitionDao = (TaskDefinitionDao) super.getBeanFactory().getBean("TaskDefinitionDao");

		// Create empty TaskDefinition
		this.taskDefinition = new TaskDefinition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		// Delete the tmp taskDefinition from the database.
		try {
			this.taskDefinitionDao.getHibernateTemplate().delete(this.taskDefinition);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDefinitionDao#saveOrUpdateTask(woops2.model.task.TaskDefinition)}.
	 * 
	 * PRINCIPLE Create a tmp taskDefinition, save it into the database with the method to
	 * test. Then look for the database to check if this tmp taskDefinition exists. To
	 * finish delete this tmp taskDefinition from the database.
	 */
	public void testSaveOrUpdateTask() {
		// Rk: the setUp method is called here.

		// Save the taskDefinition with the method to test.
		this.taskDefinitionDao.saveOrUpdateTask(this.taskDefinition);

		// Check the saving.
		String id = taskDefinition.getId();
		TaskDefinition taskTmp = (TaskDefinition) this.taskDefinitionDao.getHibernateTemplate().load(
				TaskDefinition.class, id);
		assertNotNull(taskTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDefinitionDao#getAllTasks()}.
	 * 
	 * PRINCIPLE Create a tmp taskDefinition, save it into the database. Then get all
	 * activities from the database with the method to test, and look if the
	 * size of the activities set got is >= 1. To finish delete this tmp taskDefinition
	 * from the database.
	 */
	public void testGetAllTasks() {
		// Rk: the setUp method is called here.

		// Save the taskDefinition into the database.
		this.taskDefinitionDao.getHibernateTemplate().saveOrUpdate(this.taskDefinition);

		// Look if this taskDefinition is also into the database and look if the size of
		// the set is >= 1.
		Set<TaskDefinition> taskDefinitions = this.taskDefinitionDao.getAllTask();
		assertNotNull(taskDefinitions);
		assertTrue(taskDefinitions.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDefinitionDao#getTask()}.
	 * 
	 */
	public void testGetTask() {
		// Rk: the setUp method is called here.

		// Add prooperties to the taskDefinition.
		this.taskDefinition.setName(NAME);
		this.taskDefinition.setDescription(DESCRIPTION);

		// Save the taskDefinition into the database.
		this.taskDefinitionDao.getHibernateTemplate().saveOrUpdate(this.taskDefinition);
		String id = this.taskDefinition.getId();

		// Test the method getTask with an existing taskDefinition.
		TaskDefinition taskTmp = this.taskDefinitionDao.getTask(id);
		assertNotNull(taskTmp);
		assertEquals("Name", taskTmp.getName(), NAME);
		assertEquals("Description", taskTmp.getDescription(), DESCRIPTION);

		// Test the method getTask with an unexisting taskDefinition.
		this.taskDefinitionDao.getHibernateTemplate().delete(taskDefinition);
		taskTmp = this.taskDefinitionDao.getTask(id);
		assertNull(taskTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDefinitionDao#deleteTask()}.
	 * 
	 */
	public void testDeleteTask() {
		// Rk: the setUp method is called here.

		// Save the taskDefinition into the database.
		this.taskDefinitionDao.getHibernateTemplate().saveOrUpdate(this.taskDefinition);
		String id = this.taskDefinition.getId();

		// Test the method deleteTask with an acitivity existing into the db.
		this.taskDefinitionDao.deleteTask(this.taskDefinition);

		// See if this.task is now absent in the db.
		TaskDefinition taskTmp = (TaskDefinition) this.taskDefinitionDao.getHibernateTemplate().get(
				TaskDefinition.class, id);
		assertNull(taskTmp);

		// Test the method deleteTask with a taskDefinition unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.taskDefinitionDao.deleteTask(this.taskDefinition);

		// Rk: the tearDown method is called here.
	}
}
