package test.isimarket.servants.administrationservant;

import isimarket.client.CorbaClient;
import isimarket.db.dao.WalletDao;
import isimarket.model.Wallet;
import junit.framework.TestCase;

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
