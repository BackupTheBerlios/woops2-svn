package action;


import view.PresentationConstantes;
import view.activity.ActivityItem;
import view.activity.performing.ListActivitiesForm;
import view.common.WoopsListDataModel;
import business.BusinessConstantes;
import business.user.User;

import com.cc.framework.ui.control.SimpleListControl;


public class TestHistorizedActivityAction extends WoopsActionTest {
	private ListActivitiesForm listActivitiesForm;
	
	public TestHistorizedActivityAction() {
		super();
	}
	/**
	 * Methode d'initialisation
	 */
	public void setUp() throws Exception {
		super.setUp();
		// Connexion d'un participant
		super.connect("bernard", "bernard");
	}
	
	public void testSuccessfullDisplay() {
		// On s'assure qu'on est connecte
		User user = (User) getSession().getAttribute(PresentationConstantes.KEY_USER);
		assertNotNull(user);
		
		setRequestPathInfo("/historizedActivities");
		
		//On execute l'action
		actionPerform();
		
		// On vérifie les informations dans le form
		listActivitiesForm = (ListActivitiesForm) getActionForm();
		assertNotNull(listActivitiesForm);
		
		// On s'assure que toutes les activites sont terminees
		SimpleListControl listActivities = (SimpleListControl) listActivitiesForm.getListActivities();
		assertNotNull(listActivities);
		// On recupere le model de la liste
		WoopsListDataModel model = (WoopsListDataModel) listActivities.getDataModel();
		for(int i = 0; i < model.size(); i++) {
			ActivityItem act = (ActivityItem) model.getElementAt(i);
			assertEquals(act.getState(), BusinessConstantes.ACTIVITY_STATE_FINISHED);
		}
		
		verifyInputForward();
        verifyNoActionErrors();
	}
}
