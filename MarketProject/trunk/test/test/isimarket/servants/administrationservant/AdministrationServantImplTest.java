package test.isimarket.servants.administrationservant;

import isimarket.db.dao.WalletDao;
import isimarket.model.Wallet;
import junit.framework.TestCase;
import test.isimarket.TestClient;

public class AdministrationServantImplTest extends TestCase {
	
	private TestClient client;
	private WalletDao walletDao;
	
	protected void setUp() throws Exception {
		client = new TestClient();
		client.startClient();
		walletDao = new WalletDao();
	}

	public void testCreateWallet() {
		float _cash = 1000.0f;
		client.getAdministrationServant().createWallet(_cash);
		Wallet w = walletDao.getLastInsertedWallet();
		assertEquals(w.cash, _cash);
	}

	public void testUpdateCash() {
		fail("Not yet implemented");
	}

	public void testCreateOperator() {
		fail("Not yet implemented");
	}

}
