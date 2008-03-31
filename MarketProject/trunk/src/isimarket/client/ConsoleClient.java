package isimarket.client;
import isimarket.oldservants.administrationservant.AdministrationServant;
import isimarket.oldservants.administrationservant.AdministrationServantHelper;
import isimarket.server.ServerConstants;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;


public class ConsoleClient {

	
	public static void main(String[] args) {
        try {
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			NameComponent nc = new NameComponent(ServerConstants._REF_ADMINISTRATION_SERVANT, "");
			NameComponent path[] = {nc};
			AdministrationServant administrationServantRef = AdministrationServantHelper.narrow(ncRef.resolve(path));
			System.out.println("administrationServantRef connected @ "+administrationServantRef);
//			ExactTime exactTimeRef = ExactTimeHelper.narrow(ncRef.resolve(path));
//			String time = exactTimeRef.getTime();
//			System.out.println("time => "+time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
