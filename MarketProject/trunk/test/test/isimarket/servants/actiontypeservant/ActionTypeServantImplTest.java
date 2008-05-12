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
	
	private static final int _QUANTITY = 100;
	
	private static final float _CURRENT_PRICE = 10.5f;

	private TestClient client;
	
	private ActionTypeServant atServant;

	@Before
	public void setUp() throws Exception {
		client = new TestClient();
		client.startClient();
		atServant = client.getActionTypeServantRef();
		
		atServant.createActionType(_CODE, _LABEL, ServerConstants.now(), 10.0f, _QUANTITY, _CURRENT_PRICE);
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
		
		assertNotNull("actionType not null", at);
		assertEquals("code", _CODE, at.code);
		assertEquals("label", _LABEL, at.label);
		assertEquals("quantity", _QUANTITY, at.quantity);
		assertEquals("current price", _CURRENT_PRICE, at.currentPrice);		
		
		at = null;
	}

	@Test
	public void testGetActionTypeList() {
		atServant.createActionType("code1", "label1", ServerConstants.now(), 15.0f, 10, 20.0f);
		
		ActionType[] ats = atServant.getActionTypeList();
		assertEquals("size", 2, ats.length);
		
		assertNotNull("actionType not null", ats[0]);
		assertEquals("code", _CODE, ats[0].code);
		assertEquals("label", _LABEL, ats[0].label);
		assertEquals("quantity", _QUANTITY, ats[0].quantity);
		assertEquals("current price", _CURRENT_PRICE, ats[0].currentPrice);
		
		assertNotNull("actionType1 not null", ats[1]);
		assertEquals("code", "code1", ats[1].code);
		assertEquals("label", "label1", ats[1].label);
		assertEquals("quantity", 10, ats[1].quantity);
		assertEquals("current price", 20.0f, ats[1].currentPrice);
		
		atServant.deleteActionType("code1");
		
		/*ActionType at = atServant.getActionType("code1");
		assertNull("actionType1 null", at);*/
		
		ats = null;
		
	}

	public void testGetEvents() {
		
	}

	@Test
	public void testUpdateActionType() {
		atServant.updateActionTypeQuantity(_CODE, 150);
		ActionType at = atServant.getActionType(_CODE);
		
		assertNotNull("actionType not null", at);
		assertEquals("quantity", 150, at.quantity);
		
		atServant.updateActionTypeCurrentPrice(_CODE, 12.0f);
		at = atServant.getActionType(_CODE);
		
		assertNotNull("actionType not null", at);
		assertEquals("current price", 12.0f, at.currentPrice);
	}

}
