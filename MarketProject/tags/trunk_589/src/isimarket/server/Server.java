package isimarket.server;

import isimarket.db.manager.TableManager;
import isimarket.old_servants.actiontypeservant.impl.ActionTypeServantImpl;
import isimarket.old_servants.administrationservant.impl.AdministrationServantImpl;
import isimarket.old_servants.alarmservant.impl.AlarmServantImpl;
import isimarket.old_servants.walletservant.impl.WalletServantImpl;

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
            bindReference(actionTypeServantRef, ncRef,ServerConstants._REF_ACTION_SERVANT);
            bindReference(administrationServantRef, ncRef,ServerConstants._REF_ADMINISTRATION_SERVANT);
            bindReference(alarmServantRef, ncRef,ServerConstants._REF_ALARM_SERVANT);
            bindReference(walletServantRef, ncRef,ServerConstants._REF_WALLET_SERVANT);
            
            // TODO verifier si les tables sont deja creer
            TableManager.createTables();
            
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
