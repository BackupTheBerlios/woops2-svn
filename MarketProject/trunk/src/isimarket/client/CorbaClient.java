package isimarket.client;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.actiontypeservant.ActionTypeServantHelper;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.servants.alarmservant.AlarmServant;
import isimarket.servants.alarmservant.AlarmServantHelper;
import isimarket.servants.eventservant.EventServant;
import isimarket.servants.eventservant.EventServantHelper;
import isimarket.servants.walletservant.WalletServant;
import isimarket.servants.walletservant.WalletServantHelper;
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


public class CorbaClient {

	
	private AdministrationServant administrationServantRef;
	private ActionTypeServant actionTypeServantRef;
	private AlarmServant alarmServant;
	private WalletServant walletServant;
	private EventServant eventServant;

	public void startClient() {
		String[] args = null;
        try {
        	Properties props = new Properties ();
			props.put("org.omg.CORBA.ORBInitialPort", "900");
			props.put("org.omg.CORBA.ORBInitialHost",
			"localhost");
			ORB orb = ORB.init(args, props);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			NamingContext ncRef = NamingContextHelper.narrow(objRef);
			
			administrationServantRef = AdministrationServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_ADMINISTRATION_SERVANT ));
			actionTypeServantRef = ActionTypeServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_ACTIONTYPE_SERVANT ));
			alarmServant = AlarmServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_ALARM_SERVANT ));
			walletServant = WalletServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_WALLET_SERVANT ));
			eventServant = EventServantHelper.narrow((Object) bindReference(ncRef,ServerConstants._REF_EVENT_SERVANT ));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object bindReference(NamingContext ncRef, String name) 
			throws NotFound, CannotProceed, InvalidName{
		NameComponent nc = new NameComponent(name, "");
		NameComponent path[] = {nc};
		return ncRef.resolve(path);
	}

	public AdministrationServant getAdministrationServantRef() {
		return administrationServantRef;
	}

	public ActionTypeServant getActionTypeServantRef() {
		return actionTypeServantRef;
	}

	public AlarmServant getAlarmServant() {
		return alarmServant;
	}

	public WalletServant getWalletServant() {
		return walletServant;
	}

	public EventServant getEventServant() {
		return eventServant;
	}
}
