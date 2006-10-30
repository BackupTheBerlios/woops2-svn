package view.user.summary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import view.PresentationConstantes;
import view.breakdownelement.BreakdownElementItem;
import view.breakdownelement.ListBreakDownElementsModel;
import view.common.WoopsCCAction;
import business.breakdownelement.BreakdownElement;
import business.breakdownelement.BreakdownElementManager;
import business.hibernate.exception.PersistanceException;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.common.DisplayObject;

public class ShowUserSummaryAction extends WoopsCCAction {
	
	public ShowUserSummaryAction () {
		super() ;
	}
	
	public void doExecute(ActionContext context) {
		ShowUserSummaryForm form = (ShowUserSummaryForm) context.form();
	
		HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);
		
		Integer userId = (Integer)context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);

		User user = (User)usersMap.get(userId);
		
		form.setUserID(userId.toString()) ;
		form.setFirstName(user.getFirstName());
		form.setLastName(user.getLastName());
		form.setLogin(user.getLogin());
		form.setRole(user.getRole().getCode());
		
		this.loadListBde(context, user);
		context.forwardToInput();
	}
	
	public void loadListBde (ActionContext context, User user){
		ShowUserSummaryForm form = (ShowUserSummaryForm) context.form();
		try {
			BreakdownElement b ;
			BreakdownElementItem bdei ;
			Collection listBde =  BreakdownElementManager.getInstance().getAllBreakDownElementsByUser((Integer)user.getId());
			ArrayList list = new ArrayList () ;
			for (Iterator i = listBde.iterator() ; i.hasNext() ;){
				b = (BreakdownElement)i.next() ;
				bdei = new BreakdownElementItem ();
				if (b.getEndDate() != null){
					bdei.setEndDate(b.getEndDate());
				}
				if (b.getStartDate() != null){
					bdei.setStartDate(b.getStartDate());
				}
				bdei.setPrefix(b.getPrefix());
				bdei.setName(b.getName());
				String kind = b.getKind().getName() ;
				bdei.setKind(kind);
				list.add(bdei);
			}
			DisplayObject[] data = new BreakdownElementItem[list.size()];
			data = (BreakdownElementItem[]) list.toArray(data);
			
			form.setBdeList(new ListBreakDownElementsModel(data));

		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
	}

}
