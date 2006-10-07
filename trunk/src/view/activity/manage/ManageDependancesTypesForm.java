package view.activity.manage;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDependancesTypesForm extends FWActionForm {
	private static final long serialVersionUID = 4866774687347521140L; /** Generated Serial ID */

	/* Represente l'id de l'activit� dont on veut g�rer ses d�pendances */
	private String activityId;
	
	/* Liste repr�sentant toutes les d�pendances d'une activit� */
	private SimpleListControl dependancesList = new SimpleListControl();
	
	public SimpleListControl getDependancesList() {
		return dependancesList;
	}
	
	public void setDependancesList(ListDataModel dataModel) {
		dependancesList.setDataModel(dataModel);
	}

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

	
	
}
