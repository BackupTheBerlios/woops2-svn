package view.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForward;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FormActionContext;
import com.cc.framework.common.DisplayObject;
				

public class AddUserAction extends WoopsCCAction {

	public void doExecute(ActionContext context) throws Exception {
		AddUserForm form = (AddUserForm) context.form();
		
		String mode = context.request().getParameter(PresentationConstantes.PARAM_MODE);
		
		this.setSelect(context) ;
		
		if (mode.equals(PresentationConstantes.UPDATE_MODE)){
			
			String userId = (String) context.request().getAttribute(PresentationConstantes.PARAM_USER_ID);

			HashMap usersMap = (HashMap)context.session().getAttribute(PresentationConstantes.KEY_USERS_MAP);

			User user = (User)usersMap.get(new Integer(Integer.parseInt(userId)));
			
			form.setUserId(userId);
			
			if (user!=null) {
				
				//form.s(userId.toString());
				form.setFirstName(user.getFirstName());
				form.setLastName(user.getLastName());
				form.setLogin(user.getLogin());
				form.setPassword(user.getPassword());
				form.setPassword2(user.getPassword());
				form.setRoleId(user.getRole().getId().toString());
			}		
		}
		
		context.session().setAttribute(PresentationConstantes.KEY_ROLE_OPTIONS,form.getRoleOptions());
		form.setMode(mode);
		
		context.forwardToInput();
	}

	
	
	public void add_onClick(FormActionContext context) {
		ActionForward retour = null;	
		
		AddUserForm addUserForm = (AddUserForm) context.form();

		// controle de la validation du formulaire
		context.addErrors(addUserForm.validate(context.mapping(),context.request()));

		String mode = addUserForm.getMode();
	    
		if (!context.hasErrors()) {
			User user = new User();
			UserRole userRole = new UserRole();
			
			user.setFirstName(addUserForm.getFirstName());
			user.setLastName(addUserForm.getLastName());
			user.setLogin(addUserForm.getLogin());
			user.setPassword(addUserForm.getPassword());
			
			userRole.setId(new Integer(addUserForm.getRoleId()));
			user.setRole(userRole);
			try {
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					Integer id = new Integer (Integer.parseInt((String)addUserForm.getUserId()));
					user.setId(id);
					UserManager.getInstance().update(user);
					context.addGlobalMessage("admin.msg.info.user.modify");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)){
					UserManager.getInstance().insert(user);
					context.addGlobalMessage("admin.msg.info.user.validate");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ADMIN);
			}
			catch (PersistanceException p)
			{
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,addUserForm.getUserId());
					context.addGlobalError("admin.msg.error.user.modify.global");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)){
					context.addGlobalError("admin.msg.error.user.insert.global");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			catch(DoublonException e)
			{
				if (mode.equals(PresentationConstantes.UPDATE_MODE)){
					context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,addUserForm.getUserId());
					context.addGlobalError("admin.msg.error.user.modify.doublon");
				}
				else if (mode.equals(PresentationConstantes.INSERT_MODE)){
					context.addGlobalError("admin.msg.error.user.insert.doublon");
				}
				
				retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
			}
			
        } else {
        	if (mode.equals(PresentationConstantes.UPDATE_MODE))
				context.request().setAttribute(PresentationConstantes.PARAM_USER_ID,addUserForm.getUserId());
        	retour = context.mapping().findForward(PresentationConstantes.FORWARD_ERROR);
        }
	
	context.forward(retour);
	}

	//TODO
	void setSelect (ActionContext context){
		
		AddUserForm madForm = (AddUserForm) context.form();
		RoleItem item = null;
		
		try {
			List userRoles = UserManager.getInstance().getList("UserRole");
			Collection listUserItem = new ArrayList () ;
			Iterator i = userRoles.iterator() ;
			UserRole ur ;
			while (i.hasNext())
			{
				ur = (UserRole)i.next();
				item = new RoleItem ();
				item.setId(ur.getId().toString());
				item.setCode(ur.getCode());
				listUserItem.add(item);
			}
			DisplayObject[] data = new DisplayObject[listUserItem.size()]; 
			listUserItem.toArray(data ) ; 
				
			
			ListRoleModel model = new ListRoleModel(data);
			madForm.setRoleOptions(model);
		} catch (PersistanceException e) {
			context.addGlobalError("errors.persistance.select");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);
		}
			
	}
}
