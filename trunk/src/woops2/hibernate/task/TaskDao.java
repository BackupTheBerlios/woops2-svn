package woops2.hibernate.task;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.task.Task;

/**
 * /**
 * TaskDao manage requests from the system to store Task to the database
 * 
 * @author eperico
 *
 */
public class TaskDao extends HibernateDaoSupport {
	/**
	 * Save or update a Task
	 * @param _task
	 */
	public void saveOrUpdateTask (Task _task) {
		this.getHibernateTemplate().saveOrUpdate(_task) ;
	}
	
	/**
	 * @return List <Task>
	 */
	public List <Task> getAllTask () {
		List <Task> loadAll = new ArrayList <Task>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Task.class)) ;
		return loadAll ;
	}
	
	/**
	 * @param _id
	 * @return Task
	 */
	public Task getTask (String _id) {
		return (Task) this.getHibernateTemplate().get(Task.class, _id) ;
	}
	
	/**
	 * Delete the Task
	 * @param _task
	 */
	public void deleteTask (Task _task) {
		try {
			this.getHibernateTemplate().delete(_task) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting Task into the db.
			logger.error("#### ERROR #### --- TaskDao => deleteTask: trying to delete unexisting object \n"+sose);
		}
	}
}
