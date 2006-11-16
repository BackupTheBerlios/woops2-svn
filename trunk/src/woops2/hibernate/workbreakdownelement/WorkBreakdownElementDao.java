
package woops2.hibernate.workbreakdownelement ;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import woops2.model.workbreakdownelement.WorkBreakdownElement;

/**
 * @author Sebastien
 * 
 * This class represents ... TODO
 * 
 */
public class WorkBreakdownElementDao extends HibernateDaoSupport {

	/**
	 * Save or update an activity
	 * 
	 * @param _activity
	 */
	public void saveOrUpdateWorkBreakdownElement (WorkBreakdownElement _workBreakdownElement) {
		this.getHibernateTemplate().saveOrUpdate(_workBreakdownElement) ;
	}

	/**
	 * Return a list of activities
	 * 
	 * @return
	 */
	public Set <WorkBreakdownElement> getAllWorkBreakdownElements () {
		Set <WorkBreakdownElement> loadAll = new HashSet <WorkBreakdownElement>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(WorkBreakdownElement.class)) ;
		return loadAll ;
	}

	/**
	 * Return the activity which have the id _id
	 * 
	 * @param _id
	 * @return
	 */
	public WorkBreakdownElement getWorkBreakdownElement (String _id) {
		return (WorkBreakdownElement) this.getHibernateTemplate().get(WorkBreakdownElement.class, _id) ;
	}

	/**
	 * Delete the activity
	 * 
	 * @param _activity
	 */
	public void deleteWorkBreakdownElement (WorkBreakdownElement _workBreakdownElement) {
		try {
			this.getHibernateTemplate().delete(_workBreakdownElement) ;
		}
		catch (StaleObjectStateException sose) {
			// Catch normally errors when we delete an unexisting activity into
			// the db.
			logger.error("#### ERROR #### --- WorkBreakdownElementDao => deleteWorkBreakdownElement : trying to delete unexisting object \n" + sose) ;
		}
	}
}
