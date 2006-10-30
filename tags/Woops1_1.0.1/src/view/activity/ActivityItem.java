package view.activity;



import java.util.Date;

import view.PresentationConstantes;

import business.BusinessConstantes;
import business.format.Formatage;

import com.cc.framework.common.DisplayObject;


public class ActivityItem implements DisplayObject{
	private	String id;
	private String name;
	private String details;
	private String state;
	private String action;
	private String userLastName;
	private String userFirstName;
	private boolean actionEnabled;
	private boolean deleteEnabled;
	private String startDate = "";
	private String endDate = "";
	private String onGoing = PresentationConstantes.NO;
	private boolean affectEnabled;
	
	
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		endDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		startDate = Formatage.dateToString(date, BusinessConstantes.DATE_FORMAT);
	}

	public ActivityItem() {
		super();
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the actionEnabled.
	 */
	public boolean getActionEnabled() {
		return actionEnabled;
	}

	/**
	 * @param actionEnabled The actionEnabled to set.
	 */
	public void setActionEnabled(boolean actionEnabled) {
		this.actionEnabled = actionEnabled;
	}

	public boolean getDeleteEnabled() {
		return deleteEnabled;
	}

	public void setDeleteEnabled(boolean deleteEnabled) {
		this.deleteEnabled = deleteEnabled;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public String getUserName() {
		
		return (getUserLastName()!=null)?(getUserFirstName()+" "+getUserLastName()):("");

	}

	public String getOnGoing() {
		return onGoing;
	}

	public void setOnGoing(String onGoing) {
		this.onGoing = onGoing;
	}

	public boolean isAffectEnabled() {
		return affectEnabled;
	}

	public void setAffectEnabled(boolean affectEnabled) {
		this.affectEnabled = affectEnabled;
	}


	
	
	
}
