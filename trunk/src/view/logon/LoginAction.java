package view.logon;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import view.PresentationConstantes;
import view.common.WoopsCCAction;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

import com.cc.framework.adapter.struts.ActionContext;
import com.cc.framework.security.SecurityUtil;

public class LoginAction extends WoopsCCAction {
	private Locale locale = Locale.FRENCH /** Locale par défaut pour tous les utilisateurs */;
	
	/**
	 * Vérifie si la classe courant correspond à l'action 
	 * permettant à l'utilisateur de se connecter
	 * @return <code>true</code> si l'action est la classe LoginAction
	 */
	public boolean isLogonAction() {
		return true;
	}
	
	/**
	 * @see com.cc.framework.adapter.struts.FrameworkAction#doExecute(com.cc.framework.adapter.struts.ActionContext)
	 */
	public void doExecute(ActionContext context) {
		HttpSession httpSession = context.request().getSession(false);
		
		if (httpSession.getAttribute(PresentationConstantes.KEY_USER) == null) {
			LoginForm loginForm = (LoginForm) context.form();

			// controle de la validation du formulaire
			context.addErrors(loginForm.validate(context.mapping(),context.request()));
			
		    if (!context.hasErrors()) {
				
				try {
					//Controle de la validité du couple login/mot de passe
					User user = UserManager.getInstance().isLoginValid(loginForm.getLogin(),loginForm.getPassword());
										
					if (user!=null) {
						/* On enregistre l'utilisateur comme un objet Principal, 
						ce qui permettra de controller les accès de l'utilisateur en fonction de son rôle */ 
						SecurityUtil.registerPrincipal(context.session(), user);
						
						// On change la locale de l'utilisateur
						context.session().setAttribute(org.apache.struts.Globals.LOCALE_KEY, locale);					
						
						//on met en session l'utilisateur
						httpSession.setAttribute(PresentationConstantes.KEY_USER,user);
						
						// L'utilisateur est redirigé en fonction de son rôle
						context.forwardByName(user.getRole().getCode());
					}
					else {
						context.addGlobalError("errors.login.invalide");
						context.forwardByName(PresentationConstantes.FORWARD_ERROR);
					}
				} catch (PersistanceException pe) {
					context.forwardByName(PresentationConstantes.FORWARD_ERROR);
                    context.addGlobalError("errors.persistance.global");
				}	

	        } else {
	        	context.forwardByName(PresentationConstantes.FORWARD_ERROR);
	        }
		}
		else {
			/* L'utilisateur est déjà connecté */
			User user = (User) httpSession.getAttribute(PresentationConstantes.KEY_USER);
			context.forwardByName(user.getRole().getCode());
        }
	}
}
	
	