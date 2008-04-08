package isimarket.client;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.actiontypeservant.ActionTypeServantHelper;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.server.ServerConstants;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import com.sun.corba.se.spi.orbutil.fsm.Action;


public class ConsoleClient {

	
	public static void main(String[] args) {
        try {
        	Properties props = new Properties ();
			props.put("org.omg.CORBA.ORBInitialPort", "900");
			props.put("org.omg.CORBA.ORBInitialHost",
			"localhost");
			ORB orb = ORB.init(args, props);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
//			NameComponent nc = new NameComponent(ServerConstants._REF_ADMINISTRATION_SERVANT, "");
//			NameComponent path[] = {nc};
//			AdministrationServant administrationServantRef = AdministrationServantHelper.narrow(ncRef.resolve(path));
			
			AdministrationServant administrationServantRef = AdministrationServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_ADMINISTRATION_SERVANT ));
			ActionTypeServant actionTypeServantRef = ActionTypeServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_ACTION_SERVANT ));
			
			System.out.println("administrationServantRef @ "+administrationServantRef);
			System.out.println("actionTypeServantRef @"+actionTypeServantRef);
			// TODO creer un operator test
			//administrationServantRef.createOperator("test","password",1000.0f);
			administrationServantRef.updateCash("test",3000.0f);
			
			actionTypeServantRef.createNewActionType("tst","action_de_test","00/00/0000",10.0f,1000,10.0f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Object bindReference(NamingContext ncRef, String name) 
			throws NotFound, CannotProceed, InvalidName{
		NameComponent nc = new NameComponent(name, "");
		NameComponent path[] = {nc};
		return ncRef.resolve(path);
	}

}
