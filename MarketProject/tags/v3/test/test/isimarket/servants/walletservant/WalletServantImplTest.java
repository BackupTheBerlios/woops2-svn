package test.isimarket.servants.walletservant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import isimarket.client.CorbaClient;
import isimarket.model.Action;
import isimarket.model.ActionType;
import isimarket.model.Wallet;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.walletservant.WalletServant;
import isimarket.servants.walletservant.WalletServantPackage.BadQuantityException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException;
import isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorException;
import isimarket.servants.walletservant.WalletServantPackage.WrongPasswordException;
import isimarket.server.ServerConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WalletServantImplTest {

	private static final String _LOGIN = "login";

	private static final String _PASSWORD = "password";

	private static final float _CASH = 10000.0f;

	private static final String _CODE = "code3";

	private static final String _LABEL = "label3";

	private static final int _QUANTITY = 150;

	private static final float _CURRENT_PRICE = 10.5f;

	private CorbaClient client;

	private WalletServant wtServant;

	private AdministrationServant adServant;
	
	private ActionTypeServant atServant;
	
	private Wallet w;

	@Before
	public void setUp() throws Exception {
		client = new CorbaClient();
		client.startClient();
		wtServant = client.getWalletServant();
		adServant = client.getAdministrationServantRef();
		atServant = client.getActionTypeServantRef();

		atServant.createActionType(_CODE, _LABEL, ServerConstants.now(), 10.0f,
				_QUANTITY, _CURRENT_PRICE);

		adServant.createOperator(_LOGIN, _PASSWORD, _CASH);
		
		try {
			w = wtServant.authentication(_LOGIN, _PASSWORD);
		} catch (UnknownOperatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		adServant.deleteOperator(_LOGIN);
		atServant.deleteActionType(_CODE);
		w = null;
		atServant = null;
		adServant = null;
		wtServant = null;
		client = null;
	}

	@Test
	public void testAuthentication() {
		assertNotNull("not null", w);
		assertEquals("cash", _CASH, w.cash);
	}

	@Test
	public void testBuyAction() {
		Action[] acts = null;
		try {
			wtServant.buyAction(w.walletId, _CODE, 100);
			acts = wtServant.getActionListFromWallet(w.walletId);
			
			ActionType at = atServant.getActionType(_CODE);
			assertEquals("actiontype quantity", 50, at.quantity);
			assertEquals("action quantity", 100, acts[0].quantity);
			assertEquals("cash", 10000.0f - 100 * 10.5f, w.cash);
			
		} catch (BadQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughCashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				wtServant.sellAction(w.walletId, _CODE, acts[0].actionId, 100);
			} catch (NotEnoughAvailableActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSellAction() {
		Action[] acts = null;
		try {
			wtServant.buyAction(w.walletId, _CODE, 100);
			acts = wtServant.getActionListFromWallet(w.walletId);
			wtServant.sellAction(w.walletId, _CODE, acts[0].actionId, 50);
			
			ActionType at = atServant.getActionType(_CODE);
			assertEquals("actiontype quantity", 100, at.quantity);
			assertEquals("action quantity", 50, acts[0].quantity);
			
		} catch (BadQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughCashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughAvailableActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try {
				wtServant.sellAction(w.walletId, _CODE, acts[0].actionId, 50);
			} catch (NotEnoughAvailableActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
