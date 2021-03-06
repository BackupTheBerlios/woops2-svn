
package woops2.hibernate.activity ;

import java.util.ArrayList ;
import java.util.List ;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.activity.Activity ;

/**
 * ActivityDao manage requests from the system to store Acitivties to the database
 * @author garwind
 * @author Mathieu BENOIT.
 */
public class ActivityDao extends HibernateDaoSupport {

	/**
	 * Save or update an activity
	 * @param _activity
	 */
	public void saveOrUpdateActivity (Activity _activity) {
		super.getHibernateTemplate().saveOrUpdate(_activity) ;
	}

	/**
	 * Return a list of activities
	 * @return
	 */
	public List <Activity> getAllActivities () {
		List <Activity> loadAll = new ArrayList <Activity>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Activity.class)) ;
		return loadAll ;
	}
	
	/**
	 * Return the activity which have the id _id
	 * @param _id
	 * @return
	 */
	public Activity getActivity(String _id) {
		return (Activity) this.getHibernateTemplate().get(Activity.class, _id);
	}
	
	/**
	 * Delete the activity
	 * @param _a
	 */
	public void deleteActivity(Activity _a) {
		this.getHibernateTemplate().delete(_a);
	}
}
