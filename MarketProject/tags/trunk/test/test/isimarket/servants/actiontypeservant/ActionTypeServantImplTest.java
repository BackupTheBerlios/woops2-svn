package test.isimarket.servants.actiontypeservant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import isimarket.model.ActionType;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.server.ServerConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.isimarket.TestClient;

public class ActionTypeServantImplTest {
	
	private static final String _CODE = "code";
	
	private static final String _LABEL = "label";

	private TestClient client;
	
	private ActionTypeServant atServant;

	@Before
	public void setUp() throws Exception {
		client = new TestClient();
		client.startClient();
		atServant = client.getActionTypeServantRef();
		
		atServant.createActionType(_CODE, _LABEL, ServerConstants.now(), 10.0f, 100, 10.5f);
	}
	
	@After
	public void tearDown() {
		atServant.deleteActionType(_CODE);
		atServant = null;
		client = null;
	}

	@Test
	public void testGetActionType() {
		ActionType at = atServant.getLastInsertedActionType();
		
		assertNotNull("actionType null", at);
		assertEquals("code", _CODE, at.code);
		assertEquals("label", _LABEL, at.label);
		System.out.println(at.introductionDate);
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
