package test.isimarket.servants.actiontypeservant;

import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.server.ServerConstants;

import org.junit.After;
import org.junit.Before;

import test.isimarket.TestClient;

public class ActionTypeServantImplTest {
	
	private static final String _CODE = "code";
	
	private static final String _LABEL = "label";

	private TestClient client;
	
	private ActionTypeServant atServant;

	@Before
	protected void setUp() throws Exception {
		client = new TestClient();
		atServant = client.getActionTypeServant();
		
		atServant.createActionType(_CODE, _LABEL, ServerConstants.now(), 10.0f, 100, 10.5f);
	}
	
	@After
	protected void tearDown() {
		atServant.deleteActionType(_CODE);
		atServant = null;
		client = null;
	}

	public void testGetActionType() {
		
	}

	public void testGetActionTypeList() {
		
	}

	public void testGetEvents() {
		
	}

	public void testCreateNewActionType() {
		
	}

	public void testUpdateActionType() {
		
	}

}
