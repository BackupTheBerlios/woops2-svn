package test.isimarket.dao;

import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.server.ServerConstants;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import junit.framework.TestCase;

public class ActionTypeDaoTest extends TestCase {

	protected void setUp() throws Exception {
		try {
			String[] args = null;
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			NameComponent nc = new NameComponent(ServerConstants._REF_ADMINISTRATION_SERVANT, "");
			NameComponent path[] = {nc};
//			AdministrationServant administrationServantRef = AdministrationServantHelper.narrow(ncRef.resolve(path));
//			System.out.println("administrationServantRef connected @ "+administrationServantRef);
//			
//			//administrationServantRef.createOperator("test","password",1000.0f);
//			administrationServantRef.updateCash("test",2000.0f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		fail("Not yet implemented");
	}

	public void testGet() {
		fail("Not yet implemented");
	}

	public void testGetAll() {
		fail("Not yet implemented");
	}

	public void testInsert() {
		fail("Not yet implemented");
	}

	public void testUpdateQuantity() {
		fail("Not yet implemented");
	}

	public void testUpdateCurrentPrice() {
		fail("Not yet implemented");
	}

}
