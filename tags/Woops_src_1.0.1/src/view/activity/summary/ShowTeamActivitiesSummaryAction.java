package view.activity.summary;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.performing.ListActivitiesAction;
import view.activity.performing.ListActivitiesModel;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;


/**
 * @author 
 * 
 */
public class ShowTeamActivitiesSummaryAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    

	/**
	 * Constructeur par defaut
	 */
	public ShowTeamActivitiesSummaryAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		
		try {
			this.loadList(context);
			context.forwardToInput();
	    } catch (PersistanceException pe) {
	    	logger.error(pe);
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}		
		context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
	}
	
	
	/**
	 * 
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		
		
		Collection dbData = null;
		User sessionUser = null;
		

		// Recuperation du participant connecte
    	sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
    	// recuperation de l'id du projet en cours
    	Integer BDEId = sessionUser.getDefaultBDEId();

    	// on recupere les activités de l'entitée
        dbData = ActivityManager.getInstance().getAllActivitiesByBDE(BDEId);
        
    	Collection listActivitiesItems = null;
		ActivityItem activityItem = new ActivityItem();
		listActivitiesItems = new ArrayList();
		HashMap activitiesMap = new HashMap();
        Iterator iter = dbData.iterator();
        User user = new User();
        
        // TQu'il y a des activités
		while (iter.hasNext()) {
			Activity activity = (Activity) iter.next();
			activityItem = new ActivityItem();
			activityItem.setId(activity.getId().toString());
			activityItem.setName(activity.getName());
			activityItem.setDetails(activity.getDetails());
			activityItem.setState(activity.getState().toString());
			activityItem.setStartDate(activity.getStartDate());
			activityItem.setEndDate(activity.getEndDate());

			// si l'id n'est pas nul on charge l'utilisateur associé en recuperant ses nom et prenom
			if (activity.getUserId()!=null){
				user = new User();
				user = UserManager.getInstance().getUserById(activity.getUserId());
				activityItem.setUserFirstName(user.getFirstName());
				activityItem.setUserLastName(user.getLastName());
			}
			
			listActivitiesItems.add(activityItem);
			activitiesMap.put(activity.getId(),activity);
		}
		
		
	  // chargement du form permettant l'affichage des activités 
	  // transformation en ActivityItem
	  ShowTeamActivitiesSummaryForm form = (ShowTeamActivitiesSummaryForm) context.form();
	  DisplayObject[] result = new ActivityItem[listActivitiesItems.size()];
	  listActivitiesItems.toArray(result);
		
		// Creation de la liste initialisee avec les valeurs a afficher
		ListActivitiesModel model = new ListActivitiesModel(result);
		form.setDataModel(model);
	
	}
	
	
	

	/**
	 * 
	 * @param context	contexte d'execution de la servlet
	 * @param column	colonne ? trier
	 * @param direction	direction (ASC, DESC)
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listActivities_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// R?cup?ration de la liste dans le contexte
		ListActivitiesModel model = (ListActivitiesModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demand?e et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	



}
	