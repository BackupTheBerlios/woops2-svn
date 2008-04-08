package test.isimarket.servants;

import isimarket.db.dao.WalletDao;
import isimarket.model.Wallet;
import isimarket.server.Server;
import junit.framework.TestCase;
import test.isimarket.client.CorbaClient;

public class AdministrationServantImplTest extends TestCase {
	
	private CorbaClient client;
	private WalletDao walletDao;
	
	protected void setUp() throws Exception {
		client = new CorbaClient();
		client.init(null);
		walletDao = new WalletDao();
	}

	public void testCreateWallet() {
		float _cash = 1000.0f;
		client.getAdministrationServantRef().createWallet(_cash);
		Wallet w = walletDao.getLastWallet();
		assertEquals(w.cash, _cash);
	}

	public void testUpdateCash() {
		fail("Not yet implemented");
	}

	public void testCreateOperator() {
		fail("Not yet implemented");
	}

}
