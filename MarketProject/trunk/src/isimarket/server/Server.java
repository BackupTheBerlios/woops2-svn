package isimarket.server;

import isimarket.servants.actionservant.impl.ActionServantImpl;
import isimarket.servants.administrationservant.impl.AdministrationServantImpl;
import isimarket.servants.alarmservant.impl.AlarmServantImpl;
import isimarket.servants.walletservant.impl.WalletServantImpl;
import isimarket.servants.webservant.impl.WebServantImpl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Server {
	public static void main(String[] args) {
		try{
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
 
            // create servant and register it with the ORB
            ActionServantImpl actionServantRef = new ActionServantImpl();
            orb.connect(actionServantRef);
            AdministrationServantImpl administrationServantRef = new AdministrationServantImpl();
            orb.connect(administrationServantRef);
            AlarmServantImpl alarmServantRef = new AlarmServantImpl();
            orb.connect(alarmServantRef);
            WalletServantImpl walletServantRef = new WalletServantImpl();
            orb.connect(walletServantRef);
            WebServantImpl webServantRef = new WebServantImpl();
            orb.connect(webServantRef);
 
            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
 
            // bind the Object Reference in Naming
            bindReference(actionServantRef, ncRef,"ActionServant");
            bindReference(administrationServantRef, ncRef,"AdministrationServant");
            bindReference(alarmServantRef, ncRef,"AlarmServant");
            bindReference(walletServantRef, ncRef,"WalletServant");
            bindReference(webServantRef, ncRef,"WebServant");
            
            // wait for invocations from clients
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }
 
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }

	private static void bindReference(org.omg.CORBA.portable.ObjectImpl ref,
			NamingContext ncRef, String name) throws NotFound, CannotProceed, InvalidName {
		NameComponent nc = new NameComponent(name, "");
		NameComponent path[] = {nc};
		ncRef.rebind(path, ref);
	}
}
