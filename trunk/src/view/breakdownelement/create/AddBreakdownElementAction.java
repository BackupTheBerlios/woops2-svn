package view.breakdownelement.create;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.breakdownelement.KindItem;
import view.breakdownelement.ListKindModel;
import view.common.WoopsCCAction;
import view.user.ListUsersModel;
import view.user.UserItem;
import business.BusinessConstantes;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementKind;
import business.breakdownelement.BreakdownElementManager;
import business.format.Formatage;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;

public class AddBreakdownElementAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		AddBreakdownElementForm form = (AddBreakdownElementForm) context.form();
		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
		
		// Récupération de l'id du projet
		if (mode.equals(PresentationConstantes.COPY_MODE)){
			String bkId = (String) context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);
			form.setBkId(bkId);	
		}
		else {
			// Initilialisation du formulaire
			if (mode.equals(PresentationConstantes.UPDATE_MODE)){
				String bkId = (String) context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);
	
				HashMap bkMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_BDE_MAP);
	
				BreakdownElement bke = (BreakdownElement)bkMap.get(new Integer(Integer.parseInt(bkId)));
				
				form.setBkId(bkId);				
				if (bke!=null) {
					form.setKindId(bke.getKind().getId().toString()) ;
					form.setDetails(bke.getDetails());
					form.setName(bke.getName());
					form.setPrefix(bke.getPrefix());
					
					Date startDate = bke.getStartDate();
					if (startDate != null) {						
						form.setStartDate(Formatage.dateToString(startDate, BusinessConstantes.DATE_FORMAT_BDE));					
					}
				}
				this.setUsersParticipation(context);		
			}
			else {
				Date dateDuJour = new Date();
				form.setStartDate(Formatage.dateToString(dateDuJour, BusinessConstantes.DATE_FORMAT_BDE));
			}
			
			// en mode UPDATE et INSERT on remplit le swap select
			this.setUserParticipationOptions(context);
			
		}		
		// Initilisation de la liste des processus
		this.setSelect(context) ;
		form.setMode(mode);
		context.forwardToInput();
	}
	
	/**
	 * Initilisation de la liste des participants possibles
	 * @param context : contexte d'execution de la servlet
	 * @throws PersistanceException
	 */
	private void setUserParticipationOptions(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;
		Collection userParticipationItems = null;
		UserItem item = null;
		
		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
		
		/* R?cup?ration de la liste des utilisateurs */
		userParticipationMgr = UserManager.getInstance().getList(BusinessConstantes.TABLE_USER);
		
		/* Conversion de la liste d'Activity retournee par getPossibleActivityDependances
		 * en liste d'ActivityItem */
    	Iterator iter = userParticipationMgr.iterator();
    	userParticipationItems = new ArrayList();

    	while (iter.hasNext()) {
    		User user = (User)iter.next();
			
			item = new UserItem();
			item.setId(user.getId().toString());
			item.setFirstName(user.getFirstName());
			item.setLastName(user.getLastName());
			// Code du role de l'utilisateur
			item.setRole(user.getRole().getCode());
			if (!user.getRole().getCode().equals(PresentationConstantes.ADMIN_ROLE_CODE))
					userParticipationItems.add(item);
		}
		
		/* Convertion la liste d'ActivityItem en tableau */
		DisplayObject[] data = new UserItem[userParticipationItems.size()];
		data =(UserItem[]) userParticipationItems.toArray(data);
		
		/* Met a jour l'attribut possibleDependancesOptions du Form
		 * en passant par un ListActivitiesModel */
		ListUsersModel model = new ListUsersModel(data);
		
		madForm.setUserParticipationOptions(model);
	}
	
	/**
	 * Initilisation de la liste des participants sur le processus
	 * @param context : contexte d'execution de la servlet
	 * @throws PersistanceException
	 */
	private void setUsersParticipation(ActionContext context) throws PersistanceException {
		Collection userParticipationMgr = null;

		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
		
		userParticipationMgr = UserManager.getInstance().getUsersByBDE(new Integer(Integer.parseInt(madForm.getBkId())));
	
		
		/* Convertit la liste des cles de type Integer
		 * de la liste activityDependancesKeys en tableau de cles de type String */  
    	String[] listStringKeys = new String[userParticipationMgr.size()];
		Iterator iter = userParticipationMgr.iterator();
		for (int i=0; iter.hasNext(); i++) {
			listStringKeys[i]=((User)iter.next()).getId().toString();
		}

		/* Met a jour l'attribut l'attribut usersParticipation du Form */
		madForm.setUsersParticipation(listStringKeys);			
	}
        
    /**
	 * Initilisation de la liste des processus
	 * @param context : contexte d'execution de la servlet
     */    
    private void setSelect (ActionContext context){
		
    	AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();
    	KindItem item = null;
		
		try {
			List kinds = BreakdownElementManager.getInstance().getList(BusinessConstantes.TABLE_BREAKDOWN_KIND);
			Collection listKindItem = new ArrayList () ;
			Iterator i = kinds.iterator() ;
			BreakdownElementKind ur ;
			while (i.hasNext())
			{
				ur = (BreakdownElementKind)i.next();
				item = new KindItem () ;
				item.setId(ur.getId().toString());
				item.setName(ur.getName());
				listKindItem.add(item);
			}
			DisplayObject[] data = new DisplayObject[listKindItem.size()]; 
			listKindItem.toArray(data ) ; 
				
			
			ListKindModel model = new ListKindModel(data);
			madForm.setKindOptions(model);
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
			
	}
    
    /**
     * Cette méthode permet de prendre en compte l'insertion, la mise à jour ou la copie d'un projet
	 * @param context : contexte d'execution e la servlet
     */
	public void add_onClick(FormActionContext context) {
		ActionForward retour = null;	
		
		AddBreakdownElementForm madForm = (AddBreakdownElementForm) context.form();

		// Controle de la validation du formulaire
		context.addErrors(madForm.validate(context.mapping(),context.request()));

		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
	    
		if (!context.hasErrors()) {
			// Création d'un processus avec les valeurs récupérés dans le formulaire
    		String prefix = madForm.getPrefix();
    		String name = madForm.getName();
    		String details = madForm.getDetails();
    		String dateCreation = madForm.getStartDate();
    		
    		BreakdownElement bke = new BreakdownElement ();
    		bke.setPrefix(prefix);
    		bke.setName(name);
    		bke.setDetails(details);
    		bke.setKind(new BreakdownElementKind(new Integer(Integer.parseInt(madForm.getKindId()))));
    		bke.setStartDate(Formatage.stringToDate(dateCreation));    		
    		
    		// Recuperation des participants sélectionnés
    		if (!mode.equals(PresentationConstantes.COPY_MODE)) {
    			String [] usersKeys  = madForm.getUsersParticipation();
	    		Set users = new HashSet();
	    		if (usersKeys != null) {
	        		User user;
	        		for (int i=0; i<usersKeys.length;i++) {
	        			if (usersKeys[i]!= "") {
	        				user = new User();
	            			user.setId(new Integer(Integer.parseInt(usersKeys[i])));
	                		users.add(user);        					
	        			}
	        		}
	    		}
	    		bke.setUsers(users);
    		}
    		
			try {
				/* En fonction du mode, on effectue un traitement différent,
				on différencie un insert d'une modification si l'attribut id est initialisé */ 
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					Integer id = new Integer (Integer.parseInt((String)madForm.getBkId()));
					bke.setId(id);
					BreakdownElementManager.getInstance().affectUsersToBDE(bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.modify");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)){
					BreakdownElementManager.getInstance().affectUsersToBDE(bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.insert");    					
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)){
					BreakdownElementManager.getInstance().copyBreakdownElement(new Integer(madForm.getBkId()),bke);
					context.addGlobalMessage("admin.msg.info.breakdownelement.copy");    					
				}
				// Page d'accueil
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
			
			} catch (PersistanceException p) {
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,madForm.getBkId());
					context.addGlobalError("admin.msg.error.breakdownelement.modify.global");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.insert.global");
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.copy.global");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			catch(DoublonException e) {
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID,madForm.getBkId());
					context.addGlobalError("admin.msg.error.breakdownelement.modify.doublon");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.insert.doublon");
				}
				else if (mode.equals(PresentationConstantes.COPY_MODE)) {
					context.addGlobalError("admin.msg.error.breakdownelement.copy.doublon");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			
        } else {
        	/* Si le formulaire est incomplet : en cas de mise a jour ou de copie, 
        	on recharge l'identifiant du processus */ 
        	if (mode.equals(PresentationConstantes.UPDATE_MODE) || mode.equals(PresentationConstantes.COPY_MODE))
        		context.request().setAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID, madForm.getBkId());
        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
	
		context.forward(retour);
	}    
}
