package view.breakdownelement.summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import view.user.ListUsersModel;
import view.user.UserItem;
import business.breakdownelement.BreakdownElement;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

public class ShowBdeSummaryAction extends WoopsCCAction {
	
	public ShowBdeSummaryAction () {
		super() ;
	}
	
	public void doExecute(ActionContext context) {
		ShowBdeSummaryForm form = (ShowBdeSummaryForm) context.form();
	
		HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_BDE_MAP);
		
		Integer bdeId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_BREAKDOWN_ID);

		BreakdownElement bde = (BreakdownElement)usersMap.get(bdeId);
		
		form.setPrefix(bde.getPrefix());
		form.setName(bde.getName());
		form.setDetails(bde.getDetails());
		if (bde.getStartDate() != null){
			form.setStartDate(bde.getStartDate().toString());
		}
		if (bde.getEndDate() != null){
			form.setEndDate(bde.getEndDate().toString());
		}
		form.setKind(bde.getKind().getName());
		this.loadUsers(context,bde);
		context.forwardToInput();
	}
	
	public void loadUsers (ActionContext context, BreakdownElement bde){
		ShowBdeSummaryForm form = (ShowBdeSummaryForm) context.form();
		try {
			Collection listUser =  UserManager.getInstance().getUsersByBDE((Integer)bde.getId());
			ArrayList list = new ArrayList () ;
			for (Iterator i = listUser.iterator() ; i.hasNext() ;){
				User u = (User)i.next() ;
				UserItem ui = new UserItem ();
				ui.setFirstName(u.getFirstName());
				ui.setLastName(u.getLastName());
				ui.setLogin(u.getLogin());
				ui.setRole(u.getRole().getCode());
				list.add(ui);
			}
			DisplayObject[] data = new UserItem[list.size()];
			data = (UserItem[]) list.toArray(data);
			
			form.setUsersList(new ListUsersModel (data));

		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
		
	}
	

}
