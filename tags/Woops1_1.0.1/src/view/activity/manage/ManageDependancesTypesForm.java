package view.activity.manage;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDependancesTypesForm extends FWActionForm {
	private static final long serialVersionUID = 4866774687347521140L; /** Generated Serial ID */

	/* Represente l'id de l'activité dont on veut gérer ses dépendances */
	private String activityId;
	
	/* Liste représentant toutes les dépendances d'une activité */
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
