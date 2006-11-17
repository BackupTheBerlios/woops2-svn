
package woops2.hibernate.task ;

import java.util.HashSet ;
import java.util.Set ;

import org.hibernate.StaleObjectStateException ;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.task.TaskDefinition ;

/**
 * /** TaskDefinitionDao manage requests from the system to store TaskDefinition to the database
 * 
 * @author eperico
 * 
 */
public class TaskDefinitionDao extends HibernateDaoSupport {
	/**
	 * Save or update a TaskDefinition
	 * 
	 * @param _taskDefinition
	 */
	public void saveOrUpdateTask(TaskDefinition _taskDefinition) {
		this.getHibernateTemplate().saveOrUpdate(_taskDefinition) ;
	}

	/**
	 * @return set <TaskDefinition>
	 */
	public Set<TaskDefinition> getAllTask() {
		Set<TaskDefinition> loadAll = new HashSet<TaskDefinition>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(TaskDefinition.class)) ;
		return loadAll ;
	}

	/**
	 * @param _id
	 * @return TaskDefinition
	 */
	public TaskDefinition getTask(String _id) {
		return (TaskDefinition) this.getHibernateTemplate().get(TaskDefinition.class, _id) ;
	}

	/**
	 * Delete the TaskDefinition
	 * 
	 * @param _taskDefinition
	 */
	public void deleteTask(TaskDefinition _taskDefinition) {
		try{
			this.getHibernateTemplate().delete(_taskDefinition) ;
		}
		catch(StaleObjectStateException e){
			// Catch normally errors when we delete an unexisting TaskDefinition into the
			// db.
			logger.error("#### ERROR #### --- TaskDefinitionDao => deleteTask: trying to delete unexisting object \n" + e) ;
		}
	}
}
