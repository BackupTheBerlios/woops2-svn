package view.activity.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import business.format.Controleur;

import com.cc.framework.adapter.struts.FWActionForm;

/**
 * @author Benjamin TALOU
 */

public class ManageActivityCreationForm extends FWActionForm {
	private static final long serialVersionUID = 1785279013061841305L; /** Generated Serial ID */
	
	private	String	activityId; 	/** identifiant de l'activit? */
	private String 	name; 			/** nom de l'activit? */
	private	String	details; 		/** description de l'activit? */
	private String	mode;			/** mode du formulaire ( insert ou update )	*/
	private String	caption;		/** libell? de l'entete du formulaire */
	private String	tooltipFinish;			/**	libell? du bouton finish (en rfonction du mode) */
	private String 	tooltipNext;			/** libell? du bouton next (en rfonction du mode) */
	private String	disableNext;	/** bool?en indiquant si le bouton next est d?sactiv? */
	private String  freeActivity; 	/** booleen permettant de cr?er une activit? libre */
	private String  disableFreeActivityCheckbox;
	private String  activityOnGoing; 	/** booleen permettant de cr?er une activit? sans fin */
	private String  disableActivityOnGoingCheckbox;	/** permet de savoir si le checkbox OnGoing est d?sactiv? */
	private String  event="off";	
	private String  disableEventCheckbox; /** permet de savoir si la checkbox event est desactivee */
	private String  eventDetails;
	private String  eventName;
	

	
	public ManageActivityCreationForm() {
		super();
	}

	
	/**
	 * Getters et setters generes
	 *
	 */
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getDisableNext() {
		return disableNext;
	}


	public void setDisableNext(String disableNext) {
		this.disableNext = disableNext;
	}

	
	public String getDisableFreeActivityCheckbox() {
		return disableFreeActivityCheckbox;
	}


	public void setDisableFreeActivityCheckbox(String disableFreeActivityCheckbox) {
		this.disableFreeActivityCheckbox = disableFreeActivityCheckbox;
	}


	
	public String getTooltipFinish() {
		return tooltipFinish;
	}


	public void setTooltipFinish(String tooltipFinish) {
		this.tooltipFinish = tooltipFinish;
	}


	public String getTooltipNext() {
		return tooltipNext;
	}


	public void setTooltipNext(String tooltipNext) {
		this.tooltipNext = tooltipNext;
	}


	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if (Controleur.isVide(name)){
				errors.add("name", new ActionMessage("errors.champ.obligatoire","name"));
			}
			
			if (event.equals("on") && Controleur.isVide(eventName)){
				errors.add("eventName", new ActionMessage("errors.champ.obligatoire","eventName"));
			}
			
			return errors;
		}


	public String getFreeActivity() {
		return freeActivity;
	}


	public void setFreeActivity(String freeActivity) {
		this.freeActivity = freeActivity;
	}


	public String getActivityOnGoing() {
		return activityOnGoing;
	}


	public void setActivityOnGoing(String activityOnGoing) {
		this.activityOnGoing = activityOnGoing;
	}


	public String getDisableActivityOnGoingCheckbox() {
		return disableActivityOnGoingCheckbox;
	}


	public void setDisableActivityOnGoingCheckbox(
			String disableActivityOnGoingCheckbox) {
		this.disableActivityOnGoingCheckbox = disableActivityOnGoingCheckbox;
	}


	public String getDisableEventCheckbox() {
		return disableEventCheckbox;
	}


	public void setDisableEventCheckbox(String disableEventCheckbox) {
		this.disableEventCheckbox = disableEventCheckbox;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getEventName() {
		return eventName;
	}


	public String getEventDetails() {
		return eventDetails;
	}


	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	

	
	
	
	
}
