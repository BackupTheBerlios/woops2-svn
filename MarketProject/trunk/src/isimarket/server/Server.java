package isimarket.server;

import isimarket.servants.actiontypeservant.impl.ActionTypeServantImpl;
import isimarket.servants.administrationservant.impl.AdministrationServantImpl;
import isimarket.servants.alarmservant.impl.AlarmServantImpl;
import isimarket.servants.walletservant.impl.WalletServantImpl;

import java.util.Properties;

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
			Properties props = new Properties ();
			props.put("org.omg.CORBA.ORBInitialPort", "900");
			props.put("org.omg.CORBA.ORBInitialHost",
			"localhost");
            // create and initialize the ORB
            ORB orb = ORB.init(args, props);
 
            // create servant and register it with the ORB
            ActionTypeServantImpl actionTypeServantRef = new ActionTypeServantImpl();
            orb.connect(actionTypeServantRef);
            AdministrationServantImpl administrationServantRef = new AdministrationServantImpl();
            orb.connect(administrationServantRef);
            AlarmServantImpl alarmServantRef = new AlarmServantImpl();
            orb.connect(alarmServantRef);
            WalletServantImpl walletServantRef = new WalletServantImpl();
            orb.connect(walletServantRef);
 
            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
 
            // bind the Object Reference in Naming
            bindReference(actionTypeServantRef, ncRef,ServerConstants._REF_ACTIONTYPE_SERVANT);
            bindReference(administrationServantRef, ncRef,ServerConstants._REF_ADMINISTRATION_SERVANT);
            bindReference(alarmServantRef, ncRef,ServerConstants._REF_ALARM_SERVANT);
            bindReference(walletServantRef, ncRef,ServerConstants._REF_WALLET_SERVANT);
            
            // cf script create table
            //TableManager.createTables();
            
            System.out.println("server launched ...");
            
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
