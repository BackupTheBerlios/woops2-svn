
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
		loadAll.addAll(super.getHibernateTemplate().loadAll(Activity.class)) ;
		return loadAll ;
	}
}
