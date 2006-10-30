/**
 * 
 */
package action;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;
import view.logon.LoginForm;

public class WoopsActionTest extends MockStrutsTestCase {
	protected LoginForm loginForm;
	
	/**
	 * Constructeur vide
	 */
	public WoopsActionTest() {
		super();
	}

	/**
	 * Methode d'initialisation
	 */
	public void setUp() throws Exception {
		super.setUp();
	}
	
	protected void connect(String login, String password) {
		/* On precise le contexte /WebContent
		pour trouver le fichier /WEB-INF/web.xml */
		setContextDirectory(new File("WebContent"));
		
		// On se connecte à l'application
		setRequestPathInfo("/loginUser");
		// On constitue le form et on initialise les paramètres
		loginForm = new LoginForm();
		loginForm.setLogin(login);
		loginForm.setPassword(password);
		// On fournit l'actionForm à l'action
		setActionForm(loginForm);
		
		// On execute l'action
		actionPerform();
	}
}
