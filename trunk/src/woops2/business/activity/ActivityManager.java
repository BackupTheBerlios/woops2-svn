
package woops2.business.activity ;

import java.util.List ;

import woops2.hibernate.activity.ActivityDao ;
import woops2.model.activity.Activity ;

/**
 * @author Mathieu BENOIT.
 * 
 * This class represents ... TODO
 * 
 */
public class ActivityManager {

	private ActivityDao activityDao ;

	/**
	 * TODO Method description
	 * 
	 * @return
	 */
	public List <Activity> getActivitiesList () {
		return this.activityDao.getAllActivities() ;
	}

	/**
	 * TODO Method description
	 * 
	 * @param _activity
	 */
	public void saveActivity (Activity _activity) {
		activityDao.saveOrUpdateActivity(_activity) ;
	}

	/**
	 * Getter of activityDao.
	 *
	 * @return the activityDao.
	 */
	public ActivityDao getActivityDao () {
		return this.activityDao ;
	}

	/**
	 * Setter of activityDao.
	 *
	 * @param _activityDao The activityDao to set.
	 */
	public void setActivityDao (ActivityDao _activityDao) {
		this.activityDao = _activityDao ;
	}
}
