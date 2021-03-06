package test.isimarket.servants.eventservant;

import static org.junit.Assert.assertEquals;
import isimarket.client.CorbaClient;
import isimarket.model.Event;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.eventservant.EventServant;
import isimarket.server.ServerConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventServantImplTest {
	
	private static final String _CODE = "code4";
	
	private static final String _LABEL = "labe4";
	
	private static final int _QUANTITY = 100;
	
	private static final float _CURRENT_PRICE = 10.5f;

	private CorbaClient client;
	
	private ActionTypeServant atServant;
	
	private EventServant evServant;

	@Before
	public void setUp() throws Exception {
		client = new CorbaClient();
		client.startClient();
		atServant = client.getActionTypeServantRef();
		evServant = client.getEventServant();
		
		atServant.createActionType(_CODE, _LABEL, ServerConstants.now(), 10.0f, _QUANTITY, _CURRENT_PRICE);
		evServant.createEvent(ServerConstants.now(), _CURRENT_PRICE, _CODE);
	}
	
	@After
	public void tearDown() {
		evServant.deleteEventsForActionType(_CODE);
		atServant.deleteActionType(_CODE);
		atServant = null;
		client = null;
	}

	@Test
	public void testGetEvents() {
		Event[] evts = evServant.getEventsForActionType(_CODE);
		assertEquals("size", 1, evts.length);
		
		evts = null;
		evServant.createEvent(ServerConstants.now(), 25.5f, _CODE);
		evts = evServant.getEventsForActionType(_CODE);
		assertEquals("size", 2, evts.length);
		
	}

}
