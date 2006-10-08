package view.activity.history;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.performing.ListActivitiesForm;
import view.common.WoopsCCAction;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;

public class HistorizedActivitiesAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(HistorizedActivitiesAction.class);    

	/**
	 * Constructeur par defaut
	 */
	public HistorizedActivitiesAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws Exception {
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
	 * Cette methode constitue la liste ? partir de la BD
	 * @param contexte	contexte d'execution de la servlet
	 * @throws Exception	indique qu'une erreur s'est produite pendant le traitement
	 */
	private void loadList(ActionContext context) throws Exception {
		logger.debug("ListActivitiesAction");
		
		Collection dbData = null;
		Collection listActivitiesItems = null;
		HistorizedActivityItem activityItem = null;
		User sessionUser = null;
		
		// Initialisation du form si celui-ci est nul
		if (context.form()==null) {
			context.session().setAttribute(context.mapping().getAttribute(), new ListActivitiesForm());
		}
		
		// R?cup?ration du form bean n?cessaire pour fournir les informations ? la JSP
    	ListActivitiesForm listActivitiesForm = (ListActivitiesForm) context.form();

    	// R?cup?ration de l'identifiant du participant connecte et de l'entite
    	sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
    	
    	dbData = ActivityManager.getInstance().getActivitiesHistoryByUser((Integer)sessionUser.getId(), sessionUser.getDefaultBDEId());  	
    	
    	// Constitue une liste d'ActivityItems ? partir des donn?es stock?es en BD  
    	Iterator iter = dbData.iterator();
    	listActivitiesItems = new ArrayList();
    	HashMap activitiesMap = new HashMap();
    	
    	while (iter.hasNext()) {
    		Activity activity = (Activity) iter.next();
    		activityItem = new HistorizedActivityItem();
			
    		activityItem.setId(activity.getId().toString());
    		activityItem.setName(activity.getName());
    		activityItem.setDetails(activity.getDetails());
    		activityItem.setStartDate(activity.getStartDate());
    		activityItem.setEndDate(activity.getEndDate());
    		
			listActivitiesItems.add(activityItem);
			
			//Construction de la hash map stockant la liste des activit?s
			activitiesMap.put(activity.getId(),activity);
    	}

		// Conversion de la liste en tableau d'items
		DisplayObject[] result = new HistorizedActivityItem[listActivitiesItems.size()];
		listActivitiesItems.toArray(result);
		
		// Cr?ation de la liste initialis?e avec les valeurs ? afficher
		HistorizedActivitiesModel model = new HistorizedActivitiesModel(result);
		listActivitiesForm.setDataModel(model);
		
		//Sauvegarde d'une HashMap stockant la liste des activit?s du participant
		context.session().setAttribute(PresentationConstantes.KEY_ACTIVITIES_MAP,activitiesMap);
	}
	
	/**
	 * Cette methode est appelee si le participant clique sur l'icone de tri d'une colonne
	 * @param context	contexte d'execution de la servlet
	 * @param column	colonne ? trier
	 * @param direction	direction (ASC, DESC)
	 * @throws	Exception	Indique qu'une erreur s'est produite pendant le traitement
	 */
	public void listActivities_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// R?cup?ration de la liste dans le contexte
		HistorizedActivitiesModel model = (HistorizedActivitiesModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demand?e et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	
	/**
	 * Cette methode est appel?e lorsque lorsque l'utilisateur clique sur le nom de l'activit? dans la liste
	 * @param context
	 * @param activityIdString
	 * @throws IOException
	 * @throws ServletException
	 */
	public void listActivities_onDrilldown(ControlActionContext context, String activityIdString) throws IOException, ServletException {
		context.request().setAttribute(PresentationConstantes.PARAM_ACTIVITY_ID,new Integer(activityIdString));
		
		context.forwardByName(PresentationConstantes.FORWARD_DRILLDOWN);
	}

}
