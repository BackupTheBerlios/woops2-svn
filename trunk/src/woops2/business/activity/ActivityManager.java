
package woops2.business.activity ;

import java.util.List ;

import woops2.hibernate.activity.ActivityDao ;
import woops2.model.activity.Activity ;

/**
 * ActivityManager manage operations about activity, requested by web pages 
 * (activity.jsp & activityform.jsp)
 * @author garwind
 * @author Mathieu BENOIT.
 */
public class ActivityManager {

	private ActivityDao activityDao ;

	/**
	 * Return activities list
	 * @return
	 */
	public List <Activity> getActivitiesList () {
		return this.activityDao.getAllActivities() ;
	}

	/**
	 * Save activity
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
