
package woops2.test.hibernate.task ;

import java.util.Set ;

import woops2.hibernate.task.StepDao ;
import woops2.model.task.Step ;
import woops2.model.task.TaskDefinition ;
import woops2.test.TestConfiguration ;

/**
 * TODO finir commentaires
 * 
 * @author garwind
 * 
 */
public class StepDaoTest extends TestConfiguration {
	
	private StepDao stepDao = null ;

	private Step step = null ;

	/**
	 * attributes from Element
	 */
	public static final String ID = "thisId" ;

	public static final String NAME = "thisStep" ;

	public static final String DESCRIPTION = "step" ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@ Override
	protected void setUp() throws Exception {
		super.setUp() ;

		// Get the TaskDefinitionDao Singleton for managing TaskDefinition data
		this.stepDao = (StepDao) super.getBeanFactory().getBean("StepDao") ;

		// Create empty TaskDefinition
		this.step = new Step() ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@ Override
	protected void tearDown() throws Exception {
		super.tearDown() ;

		// Delete the tmp task from the database.
		try{
			this.stepDao.getHibernateTemplate().delete(this.step) ;
		}
		catch(Exception exception){
			exception.printStackTrace() ;
		}
	}

	public void testSaveOrUpdateTask() {
		// Rk: the setUp method is called here.

		// Save the task with the method to test.
		this.stepDao.saveOrUpdateStep(this.step) ;

		// Check the saving.
		String id = step.getId() ;
		TaskDefinition taskTmp = (TaskDefinition) this.stepDao.getHibernateTemplate().load(TaskDefinition.class, id) ;
		assertNotNull(taskTmp) ;

		// Rk: the tearDown method is called here.
	}

	public void testGetAllTasks() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.stepDao.getHibernateTemplate().saveOrUpdate(this.step) ;

		// Look if this task is also into the database and look if the size of
		// the set is >= 1.
		Set<Step> steps = this.stepDao.getAllSteps() ;
		assertNotNull(steps) ;
		assertTrue(steps.size() >= 1) ;

		// Rk: the tearDown method is called here.
	}

	public void testGetTask() {
		// Rk: the setUp method is called here.

		// Add prooperties to the task.
		this.step.setName(NAME) ;
		this.step.setDescription(DESCRIPTION) ;

		// Save the task into the database.
		this.stepDao.getHibernateTemplate().saveOrUpdate(this.step) ;
		String id = this.step.getId() ;

		// Test the method getTask with an existing task.
		Step sectionTmp = this.stepDao.getStep(id) ;
		assertNotNull(sectionTmp) ;
		assertEquals("Name", sectionTmp.getName(), NAME) ;
		assertEquals("Description", sectionTmp.getDescription(), DESCRIPTION) ;

		// Test the method getTask with an unexisting task.
		this.stepDao.getHibernateTemplate().delete(step) ;
		sectionTmp = this.stepDao.getStep(id) ;
		assertNull(sectionTmp) ;

		// Rk: the tearDown method is called here.
	}

	public void testDeleteTask() {
		// Rk: the setUp method is called here.

		// Save the task into the database.
		this.stepDao.getHibernateTemplate().saveOrUpdate(this.step) ;
		String id = this.step.getId() ;

		// Test the method deleteTask with an acitivity existing into the db.
		this.stepDao.deleteStep(this.step) ;

		// See if this.task is now absent in the db.
		TaskDefinition taskTmp = (TaskDefinition) this.stepDao.getHibernateTemplate().get(TaskDefinition.class, id) ;
		assertNull(taskTmp) ;

		// Test the method deleteTask with a task unexisting into the db.
		// FIXME Normally here there are no exception thrown.
		this.stepDao.deleteStep(this.step) ;

		// Rk: the tearDown method is called here.
	}
}
