package view.activity.manage;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageActivityDependancesForm extends FWActionForm {
	private static final long serialVersionUID = -8897437228950126830L; /** Generated Serial ID */

	/* Represente l'id de l'activité dont on veut gérer ses dépendances */
	private String activityId; 
	
	/* Liste repr?sentant toutes les activit?s dont l'activit? peut d?pendre */
	private SimpleListControl possibleDependancesOptions = new SimpleListControl();
	
	/* Liste repr?sentant les cl?s des d?pendances s?lectionn?es */
	private String[] realDependancesKeys = new String[0];
	
	
	/**
	 * @return Returns the activityId.
	 */
	public String getActivityId() {
		return activityId;
	}
	
	/**
	 * @param activityId The activityId to set.
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	
	public ListDataModel getPossibleDependancesOptions() {
		return (ListDataModel) possibleDependancesOptions.getDataModel();
	}
	
	public void setPossibleDependancesOptions(ListDataModel dataModel) {
		possibleDependancesOptions.setDataModel(dataModel);
	}

	public String[] getRealDependancesKeys() {
		return realDependancesKeys;
	}
	
	public void setRealDependancesKeys(String[] realDependancesKeys) {
		this.realDependancesKeys = realDependancesKeys;
	}
	
}
