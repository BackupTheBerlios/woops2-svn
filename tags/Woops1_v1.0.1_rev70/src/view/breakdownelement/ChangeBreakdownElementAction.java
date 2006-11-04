package view.breakdownelement;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import view.PresentationConstantes;
import view.activity.performing.ListActivitiesAction;
import view.activity.performing.ListActivitiesForm;
import view.common.WoopsCCAction;
import business.user.User;

import com.cc.framework.adapter.struts.ActionContext;

public class ChangeBreakdownElementAction extends WoopsCCAction {
	private static Logger logger = Logger.getLogger(ListActivitiesAction.class);    

	/**
	 * Constructeur par defaut
	 */
	public ChangeBreakdownElementAction() {
		super();
	}

	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) throws IOException, ServletException {
		try {
			// Recuperation du form bean
	    	ListActivitiesForm form = (ListActivitiesForm) context.form();
	    	// Recuperation de l'entite par defaut
	    	Integer bdeId = new Integer(form.getBdeId());
	    	
	    	// Modifie l'entite par defaut du participant
	    	User sessionUser = (User) context.session().getAttribute(PresentationConstantes.KEY_USER);
	    	sessionUser.setDefaultBDEId(bdeId);
	    	
			context.forwardToInput();
		} catch (Throwable t) {
			logger.error(t);
			context.addGlobalError("errors.global");
			context.forwardByName(PresentationConstantes.FORWARD_ERROR);  
		}	
	}
}