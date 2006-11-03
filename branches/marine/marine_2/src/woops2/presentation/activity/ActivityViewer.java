package woops2.presentation.activity;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import woops2.business.activity.ActivityManager;
import woops2.model.activity.Activity;

public class ActivityViewer {

	private List<Activity> activitiesList;
	private ActivityManager activityManager;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	public ActivityViewer(){
		this.logger.debug("--- ActivityViewer --- == creating ..."+this);
	}
	
	public List<Activity> getActivitiesList() {
		this.activitiesList = this.activityManager.getActivitiesList();
		this.logger.debug("acti list ="+this.activitiesList);
		return activitiesList;
	}

	public void setActivitiesList(List<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}

	public ActivityManager getActivityManager() {
		return activityManager;
	}

	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}
}
