package action;

import view.PresentationConstantes;
import view.breakdownelement.create.AddBreakdownElementForm;
import view.user.ListUsersModel;

public class AddBreakdownElementActionTest extends WoopsActionTest {
	private AddBreakdownElementForm addForm;
	
	public AddBreakdownElementActionTest() {
		super();
	}
	
	public void setUp() throws Exception {
		super.setUp();
		super.connect("woops", "woops");
	}
	
	/*
	 * Test method for 'view.admin.breakdownelement.AddBreakdownElementAction.doExecute(ActionContext)'
	 */
	public void testCreateBreakdownElement() {
		
		setRequestPathInfo("/addBreakdownElement");
		addRequestParameter(PresentationConstantes.PARAM_MODE,PresentationConstantes.INSERT_MODE);
		//On execute l'action
		actionPerform();
		
		// On v?rifie les informations dans le form
		addForm = (AddBreakdownElementForm) getActionForm();
		assertNotNull(addForm);		
		ListUsersModel lst = (ListUsersModel)addForm.getUserParticipationOptions();
		assertNotNull(lst);
        verifyNoActionErrors();
	}

	/*
	 * Test method for 'view.admin.breakdownelement.AddBreakdownElementAction.add_onClick(FormActionContext)'
	 */
	public void testAdd_onClick() {
		
	}

}
