
package woops2.business.activity ;

import java.util.List ;

import woops2.hibernate.activity.ActivityDao ;
import woops2.model.activity.Activity ;

public class ActivityManager {

	private ActivityDao activityDao ;

	public ActivityDao getActivityDao () {
		return this.activityDao ;
	}

	public void setActivityDao (ActivityDao _activityDao) {
		this.activityDao = _activityDao ;
	}

	public List <Activity> getActivitiesList () {
		return this.activityDao.getAllActivities() ;
	}

	public void saveActivity (Activity _activity) {
		activityDao.saveOrUpdateActivity(_activity) ;
	}
}
