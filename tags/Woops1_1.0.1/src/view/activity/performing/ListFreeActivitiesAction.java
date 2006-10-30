package view.activity.performing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;

public class ListFreeActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListFreeActivitiesAction.class);    

	/**
	 * Constructeur par d?faut
	 */
	public ListFreeActivitiesAction() {
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
	}
	
	/**
	 * Cette methode constitue la liste a partir de la BD
	 * @param context	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		logger.debug("ListFreeActivitiesAction");
		
		Collection dbData = null;
		Collection listActivitiesItems = null;
		ActivityItem activityItem = null;
		User sessionUser = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		
		// R?cup?ration du form bean n?cessaire pour fournir les informations ? la JSP
    	ListFreeActivitiesForm listActivitiesForm = (ListFreeActivitiesForm) context.form();

    	// Recuperation du participant 
    	sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	    	
    	// Recuperation de la liste des activites
    	dbData = ActivityManager.getInstance().getFreeActivities(sessionUser.getDefaultBDEId());  	

    	// Constitue une liste d'ActivityItems ? partir des donn?es stock?es en BD  
    	Iterator iter = dbData.iterator();
    	listActivitiesItems = new ArrayList();
    	HashMap activitiesMap = new HashMap();
    	while (iter.hasNext()) {
    		Activity activity = (Activity) iter.next();
    		activityItem = new ActivityItem();
			
    		activityItem.setId(activity.getId().toString());
    		activityItem.setName(activity.getName());
    		activityItem.setDetails(activity.getDetails());
    		activityItem.setState(activity.getState().toString());
    		
    		if (activity.getOnGoing().equals(PresentationConstantes.YES))
				activityItem.setOnGoing(PresentationConstantes.YES);
    		
    		if ( activity.getEvent() == null || activity.getEvent().getOccured().equals(PresentationConstantes.YES) )
    			activityItem.setAffectEnabled(true);
    		else 
    			activityItem.setAffectEnabled(false);
    			
   
    		activityItem.setAction(PresentationConstantes.ACTIVITY_AFFECT);
    		
			
			listActivitiesItems.add(activityItem);
    	
			// Construction de la hash map stockant la liste des activit?s
			activitiesMap.put(activity.getId(),activity);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new ActivityItem[listActivitiesItems.size()];
		listActivitiesItems.toArray(result);
		
		// Cr?ation de la liste initialis?e avec les valeurs ? afficher
		ListFreeActivitiesModel model = new ListFreeActivitiesModel(result);
		listActivitiesForm.setDataModel(model);
	
		// Sauvegarde d'une HashMap stockant la liste des activit?s du participant
		context.session().setAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP,activitiesMap);
	}

	
	
	
	// ------------------------------------------------
	//          List-Control  Event Handler
    // ------------------------------------------------

	/**
	 * Cette m?thode est appel?e lorsque l'utilisateur demande un rafra?chissement de la liste 
	 * @param	context		contexte d'execution de la servlet
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listFreeActivities_onRefresh(ControlActionContext ctx) throws Exception {
		try {
			this.loadList(ctx);
		} catch (Throwable t) {
			logger.error(t);
			ctx.addGlobalError("errors.global");
		}
	}

	
	/**
	 * Cette m?thode est appel?e si le participant clique sur l'icone de tri d'une colonne
	 * @param context	contexte d'execution de la servlet
	 * @param column	colonne ? trier
	 * @param direction	direction (ASC, DESC)
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listFreeActivities_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// R?cup?ration de la liste dans le contexte
		ListFreeActivitiesModel model = (ListFreeActivitiesModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demand?e et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	
	/**
	 * Cette est appel?e si le participant souhaite commencer ou terminer une activit?
	 * @param context	contexte d'execution de la servlet
	 * @param key	identifiant d'une activit?
	 * @throws IOException	indique qu'une erreur au niveau des entr?es/sorties s'est produite 
	 * @throws ServletException	indique que le traitement demand? a g?n?r? une exception
	 */
	public void listFreeActivities_onChange(ControlActionContext context, String key) throws IOException, ServletException {
		Integer activityId = new Integer(key);
		
		try {
			Activity activity = ActivityManager.getInstance().getActivityWithDependances(activityId);
			
			// on attribue l'user en session ? l'activit?
			User user = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
			activity.setUserId((Integer) user.getId());
			
			// Met ? jour en BD l'?tat de l'activit? 
			ActivityManager.getInstance().update(activity,user);
			
			// Informe le participant que sa demande a ?t? prise en compte
			context.addGlobalMessage("msg.info.activity.affect", activity.getName());
	
		
		} catch (PersistanceException pe) {
			logger.error(pe);
			context.addGlobalError("errors.persistance.select");
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
		}
		context.forwardByName(PresentationConstantes.FORWARD_ACTION);
	}
	
	

	
	public void listFreeActivities_onEdit(ControlActionContext context, String activityIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.UPDATE_MODE);
		context.session().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,new Integer(activityIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	public void listFreeActivities_onCreate(ControlActionContext context) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		
		context.forwardByName(PresentationConstantes.FORWARD_EDIT);
	}
	
	
	
	public void listFreeActivities_onDrilldown(ControlActionContext context, String activityIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,new Integer(activityIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_DRILLDOWN);
	}
	
	
	public void listFreeActivities_onDelete(ControlActionContext context, String activityIdString) throws IOException, ServletException, PersistanceException, ForeignKeyException {
	
		try{
			
			Integer activityId = new Integer(activityIdString);
			Activity activ = ActivityManager.getInstance().getActivityById(activityId);
			String name = activ.getName();
			
			ActivityManager.getInstance().deleteLinksFromActivity(activ);
			context.addGlobalMessage("msg.error.activity.delete",name);
		}
		catch (ForeignKeyException fke) {
			logger.error(fke);
			context.addGlobalError("errors.persistance.activity.foreignKey");
		}
		catch (PersistanceException pe) {
			logger.error(pe);
			context.addGlobalError("errors.persistance.delete");
		} 
 		
		context.forwardByName(PresentationConstantes.FORWARD_ACTION);
		
	}
	
}



