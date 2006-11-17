package woops2.test.hibernate.task;

import java.util.Set;

import woops2.hibernate.task.TaskDao;
import woops2.model.task.Task;
import woops2.test.TestConfiguration;

/**
 * Unit test for TaskDao
 * 
 * @author eperico
 * 
 */
public class TaskDaoTest extends TestConfiguration {
	private TaskDao taskDao = null;

	private Task task = null;

	/**
	 * attributes from Element
	 */
	public static final String ID = "thisId";

	public static final String NAME = "thisTask";

	public static final String DESCRIPTION = "task description";

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the TaskDao Singleton for managing Task data
		this.taskDao = (TaskDao) super.getBeanFactory().getBean("TaskDao");

		// Create empty Task
		this.task = new Task();
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
			this.taskDao.getHibernateTemplate().delete(this.task);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link woops2.hibernate.task.TaskDao#saveOrUpdateTask(woops2.model.task.Task)}.
	 * 
	 * PRINCIPLE Create a tmp task, save it into the database with the method to
	 * test. Then look for the database to check if this tmp task exists. To
	 * finish delete this tmp task from the database.
	 */
	public void testSaveOrUpdateTask() {
		// Rk: the setUp method is called here.

		// Save the task with the method to test.
		this.taskDao.saveOrUpdateTask(this.task);

		// Check the saving.
		String id = task.getId();
		Task taskTmp = (Task) this.taskDao.getHibernateTemplate().load(
				Task.class, id);
		assertNotNull(taskTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDao#getAllTasks()}.
	 * 
	 * PRINCIPLE Create a tmp task, save it into the database. Then get all
	 * activities from the database with the method to test, and look if the
	 * size of the activities set got is >= 1. To finish delete this tmp task
	 * from the database.
	 */
	public void testGetAllTasks() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.taskDao.getHibernateTemplate().saveOrUpdate(this.task);

		// Look if this task is also into the database and look if the size of
		// the set is >= 1.
		Set<Task> tasks = this.taskDao.getAllTask();
		assertNotNull(tasks);
		assertTrue(tasks.size() >= 1);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDao#getTask()}.
	 * 
	 */
	public void testGetTask() {
		// Rk: the setUp method is called here.

		// Add prooperties to the task.
		this.task.setName(NAME);
		this.task.setDescription(DESCRIPTION);

		// Save the task into the database.
		this.taskDao.getHibernateTemplate().saveOrUpdate(this.task);
		String id = this.task.getId();

		// Test the method getTask with an existing task.
		Task taskTmp = this.taskDao.getTask(id);
		assertNotNull(taskTmp);
		assertEquals("Name", taskTmp.getName(), NAME);
		assertEquals("Description", taskTmp.getDescription(), DESCRIPTION);

		// Test the method getTask with an unexisting task.
		this.taskDao.getHibernateTemplate().delete(task);
		taskTmp = this.taskDao.getTask(id);
		assertNull(taskTmp);

		// Rk: the tearDown method is called here.
	}

	/**
	 * Test method for {@link woops2.hibernate.task.TaskDao#deleteTask()}.
	 * 
	 */
	public void testDeleteTask() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.taskDao.getHibernateTemplate().saveOrUpdate(this.task);
		String id = this.task.getId();

		// Test the method deleteTask with an acitivity existing into the db.
		this.taskDao.deleteTask(this.task);

		// See if this.task is now absent in the db.
		Task taskTmp = (Task) this.taskDao.getHibernateTemplate().get(
				Task.class, id);
		assertNull(taskTmp);

		// Test the method deleteTask with a task unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.taskDao.deleteTask(this.task);

		// Rk: the tearDown method is called here.
	}
}
