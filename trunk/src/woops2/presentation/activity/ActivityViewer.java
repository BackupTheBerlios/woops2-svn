
package woops2.presentation.activity ;

import java.util.List ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

import woops2.business.activity.ActivityManager ;
import woops2.model.activity.Activity ;

public class ActivityViewer {

	private List <Activity> activitiesList ;

	private ActivityManager activityManager ;

	private Activity activity ;

	protected final Log logger = LogFactory.getLog(this.getClass()) ;

	public ActivityViewer () {
		this.logger.debug("--- ActivityViewer --- == creating ..." + this) ;
		this.activity = new Activity() ;
	}

	public List <Activity> getActivitiesList () {
		this.activitiesList = this.activityManager.getActivitiesList() ;
		this.logger.debug("acti list =" + this.activitiesList) ;
		return this.activitiesList ;
	}

	/**
	 * Method for saving activity data from form
	 * 
	 * @return
	 */
	public String saveActivityAction () {
		String url = "activity" ;
		this.activityManager.saveActivity(this.activity) ;
		return url ;
	}

	public void setActivitiesList (List <Activity> _activitiesList) {
		this.activitiesList = _activitiesList ;
	}

	public ActivityManager getActivityManager () {
		return this.activityManager ;
	}

	public void setActivityManager (ActivityManager _activityManager) {
		this.activityManager = _activityManager ;
	}

	public Activity getActivity () {
		return this.activity ;
	}

	public void setActivity (Activity _activity) {
		this.logger.debug("### Activity = " + _activity + " ###") ;
		this.logger.debug("### prefix = " + _activity.getPrefix() + " ###") ;
		this.activity = _activity ;
	}
}
