package view.common;


import javax.servlet.http.HttpSession;

import view.PresentationConstantes;
import business.hibernate.PersistentObject;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.adapter.struts.FWAction;
import com.cc.framework.security.Principal;
import com.cc.framework.security.SecurityUtil;

public abstract class WoopsCCAction extends FWAction {
    
	/**
	 * Vérifie si la classe courant correspond à l'action 
	 * permettant à l'utilisateur de se connecter
	 * @return <code>true</code> si l'action est la classe LoginAction
	 */
	public boolean isLogonAction() {
		return false;
	}

	/**
	 * Vérifie si la session est valide
	 * 
	 * @param	context	ActionContext contexte d'execution de la servlet
	 * @return	true si la session est valide
	 */
	public final boolean isValidSession(ActionContext context) {
		
		HttpSession session = context.session();
		
		if ((null == session) || session.isNew()) {
			return false;
		}

		/* Si l'utilisateur n'est pas enregistré en session, 
		l'action ne sera pas executée */
		Principal principal = SecurityUtil.getPrincipal(context.session());

		if (null == principal) {
			return false;
		}

		return true;
	}
	
	
	/**
	 * @see com.cc.framework.adapter.struts.FWAction#doPreExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
    public boolean doPreExecute(ActionContext context) {
    	boolean resultat = true;

    	// L'action de Login est toujours executée sans restriction
		if (isLogonAction()) {
			return true;
		}

		// Vérifie si la session est valide
		if (!isValidSession(context)) {
	    	resultat = false;
	    		
	    	// Affiche un message à l'utilisateur
	    	context.addGlobalError("errors.session.invalid");
	    	context.forwardByName(PresentationConstantes.FORWARD_NOSESSION); 	
    	}
    	return resultat;
    }
	
    public void saveInSession(PersistentObject objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.setAttribute(objet.getClass().getName(),objet);
        }
    }

    public PersistentObject getFromSession(Class classe, ActionContext context){
        HttpSession httpSession = context.session();
        PersistentObject objet = null;
        if (httpSession!=null){
            objet = (PersistentObject) httpSession.getAttribute(classe.getName());
        }
        return objet;
    }
    
    public void removeFromSession(PersistentObject objet, ActionContext context){
        HttpSession httpSession = context.session();
        if (httpSession!=null){
            httpSession.removeAttribute(objet.getClass().getName());
        }
    }
    
}
