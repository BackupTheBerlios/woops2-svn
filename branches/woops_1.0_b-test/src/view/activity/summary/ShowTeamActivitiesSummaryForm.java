package view.activity.summary;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;


/**
 * @author 
 */

public class ShowTeamActivitiesSummaryForm extends FWActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private	String	activityId; 	/** identifiant de l'activit? */
	private	String	userId; 		/** identifiant de l'utilisateur associé */
	private String 	name; 			/** nom de l'activit? */
	private	String	details; 		/** description de l'activit? */
	private String	mode;			/** mode du formulaire ( insert ou update )	*/
	private String	caption;		/** libell? de l'entete du formulaire */
	
	
	/** Liste de contrôle des activites que l'on souhaite afficher au participant */ 
	private SimpleListControl listActivities = new SimpleListControl();
	

	/**Entité par défaut */
	private String bdeId;


	public String getBdeId() {
		return bdeId;
	}


	public void setBdeId(String bdeId) {
		this.bdeId = bdeId;
	}


	public SimpleListControl getListActivities() {
		return listActivities;
	}


	public void setListActivities(SimpleListControl listActivities) {
		this.listActivities = listActivities;
	} 
	
	/** * Modifie les données de la liste des activités du participant
	 * @param dataModel modèle de données pour le contrôleur */
	public void setDataModel(ListDataModel dataModel) {
		listActivities.setDataModel(dataModel);
	}


	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}