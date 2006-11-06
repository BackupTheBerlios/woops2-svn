
package woops2.hibernate.activity ;

import java.util.ArrayList ;
import java.util.List ;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport ;

import woops2.model.activity.Activity ;

/**
 * @author Mathieu BENOIT.
 * 
 * This class represents ... TODO
 * 
 */
public class ActivityDao extends HibernateDaoSupport {

	/**
	 * TODO Method description
	 * 
	 * @param _activity
	 */
	public void saveOrUpdateActivity (Activity _activity) {
		this.getHibernateTemplate().saveOrUpdate(_activity) ;
	}
	
	/**
	 * 
	 * @param _id
	 * @return
	 */
	public Activity getActivity(String _id) {
			return (Activity) this.getHibernateTemplate().load(Activity.class,_id);
	}
	
	/**
	 * 
	 * @param _id
	 * @return
	 */
	public void deleteActivity(Activity _activity) {
			this.getHibernateTemplate().delete(_activity);
	}

	/**
	 * TODO Method description
	 * 
	 * @return
	 */
	public List <Activity> getAllActivities () {
		List <Activity> loadAll = new ArrayList <Activity>() ;
		loadAll.addAll(this.getHibernateTemplate().loadAll(Activity.class)) ;
		return loadAll ;
	}
}
