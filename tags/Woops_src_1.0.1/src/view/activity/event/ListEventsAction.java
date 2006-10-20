/**
 * 
 */
package view.activity.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.event.Event;
import business.event.EventManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.control.ControlActionContext;

/**
 * @author Simon REGGIANI
 * Action permettant de lister les evenements qui ne sont pas encore passés du projet
 */
public class ListEventsAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListEventsAction.class);    

	public void doExecute(ActionContext context) throws Exception {
		try {
			this.loadLists(context);
			context.forwardToInput();
	    } catch (PersistanceException pe) {
	    	logger.error(pe);
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}	
	}
	
	/**
	 * Cette methode constitue les 2 listes a partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadLists(ActionContext context) throws Exception {
		ListEventsForm form = (ListEventsForm)context.form();
		EventManager evtMngr = EventManager.getInstance();

		// Recuperation du participant connecte
		User sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
    	// Recuperation de la liste des evenements pas passés du projet
		Collection listEventsNotOccured = evtMngr.getEventsByBde(sessionUser.getDefaultBDEId(),false);
	
		// Recuperation de la liste des evenements pas passés du projet
		Collection listEventsOccured = evtMngr.getEventsByBde(sessionUser.getDefaultBDEId(),true);
	
		
		// Peuplage des 2 listes
		form.setListEventsNotOccured(fromEventsToEventItems(listEventsNotOccured,context,true));
		form.setListEventsOccured(fromEventsToEventItems(listEventsOccured,context,false));
	}

	/**
	 * Permet de transformer une collection d'Event en ListEventsModel d'EventItem
	 * @param list : la collection
	 * @param context : le context
	 * @param map : booléan indiquant s'il faut enregistrer les elements de la liste dans une HasMap
	 * @return : la ListEventsModel
	 */
	private ListEventsModel fromEventsToEventItems(Collection list, ActionContext context, boolean map) {
		Iterator iter = list.iterator();
		
		Event evt;
		EventItem evtItem;
		Collection listEventsItem = new ArrayList();
		
		HashMap eventsMap = new HashMap();
		
		while(iter.hasNext()) {
			evt = (Event)iter.next();
			
			evtItem = new EventItem();
			
			// MAJ des infos de l'EventItem
			evtItem.setId(evt.getId().toString());
			evtItem.setName(evt.getName());
			evtItem.setDetails(evt.getDetails());
			evtItem.setActivityName(evt.getActivity().getName());
			
			// Ajout de l'EventItem dans l'arrayList
			listEventsItem.add(evtItem);
			
			// Ajout dans la hashMap
			if(map)
				eventsMap.put(evt.getId(),evt);
		}
		
		//Conversion de la liste en tableau d'items
		DisplayObject[] result = new EventItem[listEventsItem.size()];
		listEventsItem.toArray(result);
		
		//Sauvegarde d'une HashMap stockant la liste des activit?s du participant
		if(map)
			context.session().setAttribute(PresentationConstantes.KEY_EVENTS_MAP, eventsMap);
		
		return new ListEventsModel(result);
	}
	
	
	
	//------------------------------------------------
	//          List-Control  Event Handler
    // ------------------------------------------------

	
	public void listEventsNotOccured_onSignalOccurence(ControlActionContext context, String key) {
		HashMap eventsNotOccuredMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_EVENTS_MAP);
		Event event = (Event)eventsNotOccuredMap.get(new Integer(key));
		event.setOccured(PresentationConstantes.YES);
		
		EventManager evtMngr = EventManager.getInstance();
		try {
			evtMngr.update(event);
			context.forwardByName(PresentationConstantes.FORWARD_SIGNAL);
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		} catch (DoublonException e) {
			// Impossible de passer par la
		}
		
	}
	
	/**
	 * Cette m?thode est appel?e lorsque l'utilisateur demande un rafra?chissement de la liste 
	 * @param	context		contexte d'execution de la servlet
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listEventsOccured_onRefresh(ControlActionContext ctx) {
		try {
			this.loadLists(ctx);
		} catch (Throwable t) {
			logger.error(t);
			ctx.addGlobalError("errors.global");
		}
	}
	
	/**
	 * Cette m?thode est appel?e lorsque l'utilisateur demande un rafra?chissement de la liste 
	 * @param	context		contexte d'execution de la servlet
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listEventsNotOccured_onRefresh(ControlActionContext ctx) {
		try {
			this.loadLists(ctx);
		} catch (Throwable t) {
			logger.error(t);
			ctx.addGlobalError("errors.global");
		}
	}
	
	/**
	 * Cette methode est appelée quand l'utilisateur appuie sur le bouton "edit"
	 * dans la liste des evenements qui n'ont pas eut lieu
	 * @param ctx : contexte
	 * @param key : l'id de l'evenement à modifier
	 */
	public void listEventsNotOccured_onEdit(ControlActionContext ctx, String key) {
		ctx.request().setAttribute(PresentationConstantes.PARAM_EVENT_ID,new Integer(key));
		ctx.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
}
