package woops2.business.activity;

import java.util.List;

import woops2.hibernate.activity.ActivityDao;
import woops2.model.activity.Activity;

public class ActivityManager {
	
	private ActivityDao activityDao;

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	
	public List<Activity> getActivitiesList(){
		return this.activityDao.getAllActivities();
	}
	
	public void saveActivity(Activity a){
		activityDao.saveOrUpdateActivity(a);
	}
}
