/**
 * 
 */
package view.activity.event;

import java.util.HashMap;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.event.Event;
import business.event.EventManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;

/**
 * @author Simon REGGIANI
 * Permet de  modifier un evenement
 */
public class UpdateEventAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		UpdateEventForm form = (UpdateEventForm)context.form();
		
		/* Récupération de l'id de l'evenement à modifier */
		Integer eventId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_EVENT_ID);
		
		/* Récupération de l'evenement à modifier */
		HashMap eventsNotOccuredMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_EVENTS_MAP);
		Event event = (Event)eventsNotOccuredMap.get(eventId);
		
		/* Mise à jour des attributs du form */
		form.setEventId(eventId.toString());
		form.setName(event.getName());
		form.setDetails(event.getDetails());
		
		context.forwardToInput();

	}
	
	public void finish_onClick(FormActionContext context) {
		UpdateEventForm form = (UpdateEventForm)context.form();
		EventManager evtMngr = EventManager.getInstance();
		
		/* Récupération de l'evenement à modifier */
		HashMap eventsNotOccuredMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_EVENTS_MAP);
		Event event = (Event)eventsNotOccuredMap.get(new Integer(form.getEventId()));
		
		/* Modification des infos que l'utilisateur à put modifier */
		event.setName(form.getName());
		event.setDetails(form.getDetails());
		
		
		try {
			/* Update de l'event */
			evtMngr.update(event);
			context.forwardByName(PresentationConstantes.FORWARD_FINISH);
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} catch (DoublonException e) {
			context.addGlobalError("errors.persistance.doublon",form.getName());
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
	}

}
