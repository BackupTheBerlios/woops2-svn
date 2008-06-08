package test.isimarket.servants.administrationservant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import isimarket.client.CorbaClient;
import isimarket.db.dao.OperatorDao;
import isimarket.model.Operator;
import isimarket.servants.administrationservant.AdministrationServant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdministrationServantImplTest {
	
	private static final String _LOGIN = "login";
	
	private static final String _PASSWORD = "password";
	
	private static final float _CASH = 1000.0f;

	private CorbaClient client;
	
	private AdministrationServant adServant;
	
	private OperatorDao operatorDao;

	@Before
	public void setUp() throws Exception {
		client = new CorbaClient();
		client.startClient();
		adServant = client.getAdministrationServantRef();
		operatorDao = new OperatorDao();
		
		adServant.createOperator(_LOGIN, _PASSWORD, _CASH);
		
	}
	
	@After
	public void tearDown() {
		adServant.deleteOperator(_LOGIN);
		adServant = null;
		client = null;
	}

	@Test
	public void testCreateOperator() {
		Operator op = operatorDao.get(_LOGIN);
		
		assertNotNull("not null", op);
		assertEquals("login", _LOGIN, op.login);
		assertEquals("password", _PASSWORD, op.password);
		assertEquals("cash", _CASH, op.wallet.cash);
	}

	@Test
	public void testUpdateCash() {
		adServant.updateCash(_LOGIN, 2500.0f);
		Operator op = operatorDao.get(_LOGIN);
		
		assertEquals("cash", _CASH + 2500.0f, op.wallet.cash);
	}

}
