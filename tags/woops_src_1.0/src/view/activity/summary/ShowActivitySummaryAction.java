package view.activity.summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import view.PresentationConstantes;
import view.activity.ActivitySequenceItem;
import view.activity.ActivitySequencesModel;
import view.common.WoopsCCAction;
import business.BusinessConstantes;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.format.Formatage;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;


/**
 * @author Simon REGGIANI
 * ShowActivitySummaryAction : permet de voir la fiche de d?tail d'une activit?
 */
public class ShowActivitySummaryAction extends WoopsCCAction {
	
	/**
	 * Constructeur vide
	 *
	 */
	public ShowActivitySummaryAction() {
		super();
	}
	
	
	/**
	 * 
	 * Action a realiser avant l'affichage du formulaire
	 */

	public void doExecute(ActionContext context) {

		ShowActivitySummaryForm form = (ShowActivitySummaryForm) context.form();
	
		HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
		
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		if (activityId == null) {
			activityId = new Integer(context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID));
		}
		
		Activity activity = (Activity)activitiesMap.get(activityId);
		
		form.setActivityId(activityId.toString());
		form.setName(activity.getName());
		form.setDetails(activity.getDetails());
		String state = activity.getState().toString();
		form.setState(state);
		form.setStartDate(Formatage.dateToString(activity.getStartDate(), BusinessConstantes.DATE_FORMAT));
		form.setEndDate(Formatage.dateToString(activity.getEndDate(), BusinessConstantes.DATE_FORMAT));
		form.setOnGoing(activity.getOnGoing());
		
		try {
			setPredecessorsList(context);
			setSuccessorsList(context);
			context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		
	}
	
	
	public void setPredecessorsList(ActionContext context) throws PersistanceException {
		ShowActivitySummaryForm form = (ShowActivitySummaryForm) context.form();
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		if (activityId == null) {
			activityId = new Integer(context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID));
		}
		
		// R?cup?ration de la liste des pr?d?cesseurs de l'activit?
		Collection predecessorsListMngr = ActivityManager.getInstance().getActivitySequencesPredecessors(activityId);
	
		//Convertion de cette liste en une liste d'ActivitySequenceItem
		Iterator iter = predecessorsListMngr.iterator();
		ActivitySequence activitySequence;
		
		Collection activitySequenceItems = new ArrayList();
		while(iter.hasNext()) {
			activitySequence = (ActivitySequence)iter.next();
			ActivitySequenceItem activitySequenceItem = new ActivitySequenceItem();
			activitySequenceItem.setId(activitySequence.getId().toString());
			activitySequenceItem.setPredecessor(activitySequence.getPredecessor().getName());
			activitySequenceItem.setPredecessorState(activitySequence.getPredecessor().getState().toString());
			activitySequenceItem.setLinkType(activitySequence.getLinkType().getName());
			activitySequenceItems.add(activitySequenceItem);
		}
		
		/* Convertion de cette liste en tableau */
		DisplayObject[] data = new ActivitySequenceItem[activitySequenceItems.size()];
		data = (ActivitySequenceItem[]) activitySequenceItems.toArray(data);
		
		form.setPredecessorsList(new ActivitySequencesModel(data));
			
	}
	
	
	public void setSuccessorsList(ActionContext context) throws PersistanceException {
		ShowActivitySummaryForm form = (ShowActivitySummaryForm) context.form();
		Integer activityId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		if (activityId == null) {
			activityId = new Integer(context.request().getParameter(PresentationConstantes.PARAM_ACTIVITY_ID));
		}
		
		// R?cup?ration de la liste des successeurs de l'activit?
		Collection successorsListMngr = ActivityManager.getInstance().getActivitySequencesSuccessors(activityId);
	
		//Convertion de cette liste en une liste d'ActivitySequenceItem
		Iterator iter = successorsListMngr.iterator();
		ActivitySequence activitySequence;
		
		Collection activitySequenceItems = new ArrayList();
		while(iter.hasNext()) {
			activitySequence = (ActivitySequence)iter.next();
			ActivitySequenceItem activitySequenceItem = new ActivitySequenceItem();
			activitySequenceItem.setId(activitySequence.getId().toString());
			activitySequenceItem.setSuccessor(activitySequence.getSuccessor().getName());
			activitySequenceItem.setSuccessorState(activitySequence.getSuccessor().getState().toString());
			activitySequenceItem.setLinkType(activitySequence.getLinkType().getName());
			activitySequenceItems.add(activitySequenceItem);
		}
		
		/* Convertion de cette liste en tableau */
		DisplayObject[] data = new ActivitySequenceItem[activitySequenceItems.size()];
		data = (ActivitySequenceItem[]) activitySequenceItems.toArray(data);
		
		form.setSuccessorsList(new ActivitySequencesModel(data));
			
	}
}
