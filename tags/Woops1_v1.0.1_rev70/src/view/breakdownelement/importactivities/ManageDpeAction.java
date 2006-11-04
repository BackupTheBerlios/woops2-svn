package view.breakdownelement.importactivities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import view.util.StringOperation;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
import com.cc.framework.common.SortOrder;
import com.cc.framework.ui.control.ControlActionContext;
import com.cc.framework.ui.control.SimpleListControl;
import com.cc.framework.ui.model.ListDataModel;

public class ManageDpeAction extends WoopsCCAction{

	public void doExecute(ActionContext context) throws Exception {
		// recuperation de la liste d'activites
		ManageDpeForm form = (ManageDpeForm)context.form() ;
				
		ListDataModel activityname = this.loadAct (context) ;
		form.setListActivities(activityname) ;
		context.forwardToInput();
		
	}
	
	private ListDataModel loadAct(ActionContext context) {
		List listActivities = (List)context.request().getAttribute(PresentationConstantes.PARAM_ACTIVITIES) ;
		ArrayList list = new ArrayList () ;
		IAItem item ;
		String idString = (String) context.session().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID) ;
		Integer id = Integer.decode(idString) ;
		try {
			Collection c = ActivityManager.getInstance().getAllActivitiesByBDE(id);
			Activity a ;
			for (int i = 0 ; i < listActivities.size() ; i++)
			{
				a = (Activity)listActivities.get(i) ;
				if (!this.existe(c,a.getName())){
					item = new IAItem() ;
					item.setId(Integer.toString(i));
					item.setName(a.getName());
					list.add(item);
				}
				
			}
			DisplayObject[] result = new IAItem[list.size()];
			list.toArray(result);
			
			// Création de la liste initialisée avec les valeurs à afficher
			ListIAModel model = new ListIAModel(result);
			return model;
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		return null;
		
	}

	public void listActivities_onSort(ControlActionContext context, String column, SortOrder direction) throws Exception {
		// Récupération de la liste dans le contexte
		ListIAModel model = (ListIAModel) context.control().getDataModel();
		
		// Effectue le tri sur la colonne demandée et enregistre les modification au niveau du contexte
		model.sortByColumn(column, direction);		
		context.control().execute(context, column,  direction);
	}
	private boolean existe (Collection c, String chaine){
		boolean trouve = false ;
		for (Iterator i = c.iterator() ; i.hasNext() && !trouve ; ){
			Activity a = (Activity)i.next() ;
			String chaine1 = StringOperation.sansAccent(a.getName()) ;
			String chaine2 = StringOperation.sansAccent(chaine) ;
			trouve = chaine1.equals(chaine2);
		}
		return trouve;
	}
	public void confirm_onClick(FormActionContext context) {
		ManageDpeForm form = (ManageDpeForm)context.form() ;
		SimpleListControl liste = form.getListActivities() ;
		ListIAModel listeDM = (ListIAModel) liste.getDataModel() ;
		IAItem item ;
		Activity activity ;
		String idString = (String) context.session().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID) ;
		Integer id = Integer.decode(idString) ;
		try {
			for (int i = 0 ; i < listeDM.size() ; i++){
				item = (IAItem)listeDM.getElementAt(i) ;
				if (item.getSelectionne()!=null){
					activity = new Activity();
					activity.setName(item.getName());
					activity.setUserId(null);
					activity.setBdeId(id);
					activity.setOnGoing(PresentationConstantes.NO);
					ActivityManager.getInstance().insert(activity);
					
				}
			}
			context.addGlobalMessage("admin.manageDpe.confirmation") ;
			context.forwardByName(PresentationConstantes.FORWARD_SUCCESS);
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.globals");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} catch (DoublonException e) {
			context.addGlobalError("errors.persistance.doublon");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		} 
		
	}
	
}
