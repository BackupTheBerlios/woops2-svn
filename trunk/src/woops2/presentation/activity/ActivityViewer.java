
package woops2.presentation.activity ;

import java.util.List ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

import woops2.business.activity.ActivityManager ;
import woops2.model.activity.Activity ;

/**
 * Managed-Bean link to activity.jsp and activitform.jsp
 * 
 * @author garwind
 * @author deder.
 */
public class ActivityViewer {

	private List <Activity> activitiesList ;

	private ActivityManager activityManager ;

	private Activity activity ;

	protected final Log logger = LogFactory.getLog(this.getClass()) ;

	/**
	 * Constructor.
	 * 
	 */
	public ActivityViewer () {
		this.logger.debug("--- ActivityViewer --- == creating ..." + this) ;
		this.activity = new Activity() ;
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

	/**
	 * Getter of activitiesList.
	 * 
	 * @return the activitiesList.
	 */
	public List <Activity> getActivitiesList () {
		this.activitiesList = this.activityManager.getActivitiesList() ;
		this.logger.debug("acti list =" + this.activitiesList) ;
		return this.activitiesList ;
	}

	/**
	 * Setter of activitiesList.
	 * 
	 * @param _activitiesList
	 *            The activitiesList to set.
	 */
	public void setActivitiesList (List <Activity> _activitiesList) {
		this.activitiesList = _activitiesList ;
	}

	/**
	 * Getter of activity.
	 * 
	 * @return the activity.
	 */
	public Activity getActivity () {
		return this.activity ;
	}

	/**
	 * Setter of activity.
	 * 
	 * @param _activity
	 *            The activity to set.
	 */
	public void setActivity (Activity _activity) {
		this.logger.debug("### Activity = " + _activity + " ###") ;
		this.logger.debug("### prefix = " + _activity.getPrefix() + " ###") ;
		this.activity = _activity ;
	}

	/**
	 * Getter of activityManager.
	 * 
	 * @return the activityManager.
	 */
	public ActivityManager getActivityManager () {
		return this.activityManager ;
	}

	/**
	 * Setter of activityManager.
	 * 
	 * @param _activityManager
	 *            The activityManager to set.
	 */
	public void setActivityManager (ActivityManager _activityManager) {
		this.activityManager = _activityManager ;
	}
}
