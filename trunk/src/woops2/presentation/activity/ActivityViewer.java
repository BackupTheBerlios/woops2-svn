package woops2.presentation.activity;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import woops2.business.activity.ActivityManager;
import woops2.model.activity.Activity;

public class ActivityViewer {

	private List<Activity> activitiesList;
	private ActivityManager activityManager;
	private Activity activity;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	public ActivityViewer(){
		this.logger.debug("--- ActivityViewer --- == creating ..."+this);
		activity = new Activity();
	}
	
	public List<Activity> getActivitiesList() {
		this.activitiesList = this.activityManager.getActivitiesList();
		this.logger.debug("acti list ="+this.activitiesList);
		return activitiesList;
	}
	/**
	 * Method for saving activity data from form
	 * @return
	 */
	public String saveActivityAction(){
		String url = "activity";
		activityManager.saveActivity(this.activity);
		return url;
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		logger.debug("### Activity = "+activity+ " ###");
		logger.debug("### prefix = "+activity.getPrefix()+ " ###");
		this.activity = activity;
	}
}
