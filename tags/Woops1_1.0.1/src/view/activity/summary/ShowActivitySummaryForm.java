package view.activity.summary;

import java.util.ArrayList;
import java.util.Collection;

import view.activity.ActivitySequencesModel;

import com.cc.framework.adapter.struts.FWActionForm;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

/**
 * @author Simon REGGIANI
 */

public class ShowActivitySummaryForm extends FWActionForm {
	private static final long serialVersionUID = -7623927860007234202L; /** Generated Serial ID */
	private	String	activityId; /** identifiant de l'activit? */
	private String 	name; 		/** nom de l'activit? */
	private	String	details; 	/** description de l'activit? */
	private String	state;		/** ?tat de l'activt? */
	private String	startDate;	/** date de d?but de l'activit? */
	private String	endDate;	/** date de fin de l'activit? */
	private String 	onGoing;	/** tache sans fin */
	
	private SimpleListControl	predecessorsList;	/** liste des d?pendances entrantes */
	private SimpleListControl	successorsList;		/** liste des d?pendances sortantes */
	
	
	
	
	public ShowActivitySummaryForm() {
		super();
		predecessorsList = new SimpleListControl();
		successorsList = new SimpleListControl();
	}

	
	/**
	 * Getters et setters generes
	 *
	 */
	
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


	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}


	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return Returns the predecessorsList.
	 */
	public SimpleListControl getPredecessorsList() {
		return predecessorsList;
	}


	/**
	 * @param model The model to set to predecessorsList.
	 */
	public void setPredecessorsList(ListDataModel model) {
		this.predecessorsList.setDataModel(model);
	}


	/**
	 * @return Returns the successorsList.
	 */
	public SimpleListControl getSuccessorsList() {
		return successorsList;
	}




	/**
	 * @param model The model to set to successorsList.
	 */
	public void setSuccessorsList(ListDataModel model) {
		this.successorsList.setDataModel(model);
	}


	public String getOnGoing() {
		return onGoing;
	}


	public void setOnGoing(String onGoing) {
		this.onGoing = onGoing;
	}


	public Collection getCollecPredecessorList() {
		ActivitySequencesModel model = (ActivitySequencesModel)predecessorsList.getDataModel();
		Collection col = new ArrayList();
		int i ;
		for(i=0;i<model.size();i++){
			col.add(model.getElementAt(i)); 
		}
		return col;
	}


	public Collection getCollecSuccessorList() {
		ActivitySequencesModel model = (ActivitySequencesModel)successorsList.getDataModel();
		Collection col = new ArrayList();
		int i ;
		for(i=0;i<model.size();i++){
			col.add(model.getElementAt(i)); 
		}
		return col;
	}
	
	


	
}
