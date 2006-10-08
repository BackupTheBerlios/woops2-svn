package action;

import view.PresentationConstantes;
import view.admin.AdminForm;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;
import business.user.UserRole;

import com.cc.framework.ui.control.SimpleListControl;

public class AdminActionTest extends WoopsActionTest {
	
	private AdminForm adminForm;
	
	public AdminActionTest() {
		super();
	}
	/**
	 * Methode d'initialisation
	 */
	public void setUp() throws Exception {
		super.setUp();
		// Connexion d'un administrateur
		super.connect("woops", "woops");
	}
	
	public void testSuccessfullDisplay() {
		// On s'assure qu'on est connecte
		User user = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(user);
		
		setRequestPathInfo("/admin");
		
		//On execute l'action
		actionPerform();
		
		// On v?rifie les informations dans le form
		adminForm = (AdminForm) getActionForm();
		assertNotNull(adminForm);
				
		//On s'assure que toutes les BreakDownElements sont terminees
		SimpleListControl listBreakDownElements = (SimpleListControl) adminForm.getListBreakDownElements();
		assertNotNull(listBreakDownElements);
		
		//On s'assure que toutes les users sont terminees
		SimpleListControl listUsers = (SimpleListControl) adminForm.getListUsers();
		assertNotNull(listUsers);
		
		
		verifyInputForward();
        verifyNoActionErrors();
	}
	
	public void testSuccessfulDeleteUser() {
		
		//On s'assure qu'on est connecte
		User currentUser = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(currentUser);
		
		// on ajoute d'abord un utilisateur "testSuccessfulDeleteUser"
		User user = new User();
		UserRole userRole = new UserRole();
		
		user.setFirstName("testSuccessfulDeleteUser");
		user.setLastName("testSuccessfulDeleteUser");
		user.setLogin("testSuccessfulDeleteUser");
		user.setPassword("testSuccessfulDeleteUser");
		
		userRole.setId(new Integer(1));
		user.setRole(userRole);
		Integer userId = null; 
		try {
			userId = (Integer) UserManager.getInstance().insert(user);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DoublonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// on ex?cute la methode doExecute() pour mettre ? jour context
//		setRequestPathInfo("/admin");
//		actionPerform();
//		
		// on r?cup?re nouvel adminForm
//		adminForm = (AdminForm) getActionForm();
//		
//		assertNotNull(adminForm);	

		
		setRequestPathInfo("/admin");
		
		setActionForm(adminForm);
		
		//addRequestParameter("do", "listUsers_onDelete");
		addRequestParameter("ctrl", "listUsers");
		addRequestParameter("action", "Delete");
		addRequestParameter("param", userId.toString());
		System.out.println(this.getRequest().getQueryString());
		//On execute l'action pour supprimer cet utilisateur "testSuccessfulDeleteUser"
		actionPerform();
		
		// on v?rifier que l'utilisateur "testSuccessfulDeleteUser" n'existe plus
				
		verifyForward(PresentationConstantes.FORWARD_DELETE_USER);
//		verifyInputForward();
		verifyNoActionErrors();
	}
	
	public void testFaileDeleteUser() {
		
		String [] errors = new String[1];
		errors[0] = "errors.admin.deleteCurrentUser";
		
//		On s'assure qu'on est connecte
		User currentUser = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(currentUser);
		
		setRequestPathInfo("/admin");
		
		addRequestParameter("ctrl", "listUsers");
		addRequestParameter("action", "Delete");
		addRequestParameter("param", (String) currentUser.getId());
		
		actionPerform();
		
		verifyForward(PresentationConstantes.FORWARD_ERROR);
        verifyActionErrors(errors);
	}
	
	public void testSuccessfulDeleteBDE() {
		
	}
}
