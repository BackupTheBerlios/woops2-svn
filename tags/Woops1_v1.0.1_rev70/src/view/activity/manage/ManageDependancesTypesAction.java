package view.activity.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

import view.PresentationConstantes;
import view.activity.ActivitySequenceItem;
import view.activity.ActivitySequencesModel;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.activity.sequencetype.ActivitySequenceTypeManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.ui.control.SimpleListControl;


/**
 * @author Simon Reggiani
 * ManageDependancesTypesAction : Action permetant de g?rer les types des d?pendances d'une activit?
 */
public class ManageDependancesTypesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ManageDependancesTypesAction.class);   
	
	ActionForward forward = null;
	
	/**
	 * Constructeur vide
	 *
	 */
	public ManageDependancesTypesAction() {
		super();
	}


	
	/**
	 * @param context : contexte de l'action. Contient le form, la requette, ...
	 * @throws IOException, ServletException
	 * Permet d'initialiser le formulaire de gestion des types des d?pendances d'une activit?.
	 * 	-> Initialise la liste des d?pendances avec leur type
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		logger.debug("ManageActivityDependancesAction.doExecute()");
		
		if (context.form()==null) {
			context.request().setAttribute(context.mapping().getAttribute(), new ManageActivityDependancesForm());
		}
	
		try {
			/** Met ? jour les attributs du ManageDependancesTypesForm **/
			setDependancesList(context);
		} catch (PersistanceException pe) {
			context.addGlobalError("errors.persistance.global");
		} catch (Throwable t) {
			context.addGlobalError("errors.global");
			t.printStackTrace();
		}
		
		/** Affiche la page avec la liste ? boutons radio **/
	    context.forward(context.mapping().findForward(PresentationConstantes.FORWARD_SUCCESS)); 
	}
	
	
	private void setDependancesList(ActionContext context) throws PersistanceException {
		Collection dependancesListMgr;
		
		
		/* Recup?ration de l'id de l'activit? dont on veut g?rer les d?pendances dans la requete*/
		Integer activityId = (Integer)context.session().getAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		/* R?cup?ration des la liste des d?pendances de cette activit? en BD */
		dependancesListMgr = ActivityManager.getInstance().getActivitySequencesPredecessors(activityId);
	
		/* Convertion de cette liste en liste d'ActivitySequenceType */
		Iterator iter = dependancesListMgr.iterator();
		ActivitySequence activitySequence;
		
		Collection activitySequenceItems = new ArrayList();
		while(iter.hasNext()) {
			activitySequence = (ActivitySequence)iter.next();
			ActivitySequenceItem activitySequenceItem = new ActivitySequenceItem();
			activitySequenceItem.setId(activitySequence.getId().toString());
			activitySequenceItem.setPredecessor(activitySequence.getPredecessor().getName());
			activitySequenceItem.setLinkType(activitySequence.getLinkType().getName());
			
			//  si le predecesseur  est onGoing, impossible de mettre liens Finish to X
			if (activitySequence.getPredecessor().getOnGoing().equals(PresentationConstantes.YES)) {
				activitySequenceItem.setFinishToFinishEditable(false);
				activitySequenceItem.setFinishToStartEditable(false);
			}
			
			//  si le successeur  est onGoing, impossible de mettre liens X to Finish
			if (activitySequence.getSuccessor().getOnGoing().equals(PresentationConstantes.YES)) {
				activitySequenceItem.setStartToFinishEditable(false);
				activitySequenceItem.setFinishToFinishEditable(false);
			}
			
			String state = MessageResources.getMessageResources("ApplicationResources").getMessage(activitySequence.getPredecessor().getState().toString());
			activitySequenceItem.setPredecessorState(state);
			activitySequenceItems.add(activitySequenceItem);
		}
			
		/* Convertion de cette liste en tableau */
		DisplayObject[] data = new ActivitySequenceItem[activitySequenceItems.size()];
		data = (ActivitySequenceItem[]) activitySequenceItems.toArray(data);
		
	
		/* Sauvegarde de la simpleListControl dans la session pour que la page jsp y accede*/
		SimpleListControl list = new SimpleListControl();
		list.setDataModel(new ActivitySequencesModel(data));
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST,list);
		
		/* Sauvegarde de la liste d'ActivitySequence r?cup?r?e avec le manager */
		context.session().setAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR,dependancesListMgr);
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton finish (retour ? listActivities)
	 */
	
	public void finish_onClick(FormActionContext context) {
		
		/* R?cup?ration de la simpleListeControle dans la session */
		SimpleListControl dependancesListSlc = (SimpleListControl)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST);
		/* Supression de l'attribut de la session */
		context.session().removeAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST);
		
		/* R?cup?ration de la liste dans la session */
		Collection dependancesListMgr = (Collection)context.session().getAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR);
		/* Supression de l'attribut de la session */
		context.session().removeAttribute(PresentationConstantes.KEY_DEPENDANCES_LIST_MNGR);
		
		ActivitySequencesModel listDataModel = (ActivitySequencesModel)dependancesListSlc.getDataModel();
		
		ActivitySequenceManager actSeqMngr = ActivitySequenceManager.getInstance();
		ActivitySequenceTypeManager actSeqTypeMngr = ActivitySequenceTypeManager.getInstance();
		
		ActivitySequenceType actSeqType;
		ActivitySequenceItem actSeqItem;
		ActivitySequence actSeq;
		
		Iterator iter = dependancesListMgr.iterator();
		boolean allUnchanged = true;
		for(int i=0; iter.hasNext(); i++) {
			actSeqItem = (ActivitySequenceItem)listDataModel.getElementAt(i);
			try {
				actSeq = (ActivitySequence)iter.next();
				/* v?rification que l'utilisateur ait bien modifi? le type 
				 * ( on ne va pas updater alors qu'il n'y a pas eut de changement ) */
				if ( !actSeq.getLinkType().getName().equals(actSeqItem.getLinkType()) ) {
					actSeqType = actSeqTypeMngr.getActivitySequenceTypeByName(actSeqItem.getLinkType());
					actSeq.setLinkType(actSeqType);
					actSeqMngr.update(actSeq,(User) context.session().getAttribute(PresentationConstantes.KEY_USER));
			
					allUnchanged=false;
				}	
			} catch (PersistanceException e) {
				context.addGlobalError("errors.persistance.global");
			} catch (DoublonException e) {
				context.addGlobalError("errors.persistance.doublon");
			}
		}
		
		if (!allUnchanged) {
			/* R?cup?ration de l'activit? dans la hashmap pour connaitre son nom */
			HashMap activitiesMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP);
			
			ManageDependancesTypesForm form = (ManageDependancesTypesForm) context.form();
			
			Activity activity = (Activity)activitiesMap.get(new Integer(form.getActivityId()));
			
			context.addGlobalMessage("msg.info.activity.dependancesTypes.saved",activity.getName());
		}
		
		
		// Suppression de l'activityId en session
		context.session().removeAttribute(PresentationConstantes.PARAM_ACTIVITY_ID);
		
		/** Appel de la page de garde **/
		
		// on est obligé de chargé l'activité pour forwarder correctement
		ManageDependancesTypesForm form = (ManageDependancesTypesForm) context.form();
		Integer activityId = new Integer(form.getActivityId());
		Activity activity = new Activity();
		try {
			activity = ActivityManager.getInstance().getActivityById(activityId);
		} 
		catch (PersistanceException e) {}
		
		// on forwarde selon le cas
		if (activity.getUserId()!=null){
			context.forwardByName(PresentationConstantes.FORWARD_FINISH);
		}else{
			context.forwardByName(PresentationConstantes.FORWARD_FINISH_FREE_ACTIVITIES);
		}
	}
	
	/**
	 * 
	 * @param		ctx		FormActionContext
	 * 
	 * Action a realiser lorsque l'utilisateur clique sur le bouton previous (manageActivityCreation)
	 */
	
	public void previous_onClick(FormActionContext context) {
		context.forwardByName(PresentationConstantes.FORWARD_PREVIOUS);
	}

}
