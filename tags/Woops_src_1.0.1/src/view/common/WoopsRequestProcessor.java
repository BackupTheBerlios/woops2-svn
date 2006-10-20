package view.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import view.PresentationConstantes;
import business.user.User;

import com.cc.framework.adapter.struts.FWRequestProcessor;


public class WoopsRequestProcessor extends FWRequestProcessor {

	/**
	 * Constructeur
	 */
	public WoopsRequestProcessor() {
		super();
	}

	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException, ServletException {
		try {
			return super.processRoles(request, response, mapping);
		} catch (ServletException se) {
			ActionForward forward = null;
			
			// R�cup�ration de l'identifiant du participant connect�
	    	User sessionUser = (User) request.getSession().getAttribute(PresentationConstantes.KEY_USER);
			if (sessionUser != null) {
				/* L'utilisateur n'a pas les permissions pour acc�der � la page,
				il est redirig� vers sa page d'accueil */
				forward = mapping.findForward(sessionUser.getRole().getCode());
			} else {
				forward = mapping.findForward(PresentationConstantes.FORWARD_NOSESSION);
			}
			request.getRequestDispatcher(forward.getPath()).forward(request, response);
			return false;
		}
	}
}
