package woops2.hibernate.task;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.task.TaskDescriptor;

/**
 * TaskDescriptorDao manage requests from the system to store TaskDescriptor to the database
 * 
 * @author eperico
 *
 */
public class TaskDescriptorDao extends HibernateDaoSupport {
	
	/**
	 * Save or update a TaskDescriptor
	 * @param _taskdescriptor
	 */
	public void saveOrUpdateTaskDescriptor (TaskDescriptor _taskdescriptor) {
		this.getHibernateTemplate().saveOrUpdate(_taskdescriptor) ;
	}
	
	/**
	 * Return a list of TaskDescriptor
	 * @return List <TaskDescriptor>
	 */
	public List <TaskDescriptor> getAllTaskDescriptor () {
		List <TaskDescriptor> loadAll = new ArrayList <TaskDescriptor>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(TaskDescriptor.class)) ;
		return loadAll ;
	}
	
	/**
	 * Return the TaskDescriptor which have the id _id
	 * @param _id
	 * @return TaskDescriptor
	 */
	public TaskDescriptor getTaskDescriptor (String _id) {
		return (TaskDescriptor) this.getHibernateTemplate().get(TaskDescriptor.class, _id) ;
	}
	
	/**
	 * Delete the TaskDescriptor
	 * @param _taskdescriptor
	 */
	public void deleteTaskDescriptor (TaskDescriptor _taskdescriptor) {
		try {
			this.getHibernateTemplate().delete(_taskdescriptor) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting TaskDescriptor into the db.
			logger.error("#### ERROR #### --- TaskDescriptorDao => deleteTaskDescriptor : trying to delete unexisting object \n"+sose);
		}
	}
}
