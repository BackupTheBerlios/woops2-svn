package isimarket.client;

import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.actiontypeservant.ActionTypeServantHelper;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.server.ServerConstants;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class CorbaClient {

	private ORB orb;
	private org.omg.CORBA.Object objRef;
	private NamingContext ncRef;
	private NameComponent paths[];
	private AdministrationServant administrationServantRef;
	private ActionTypeServant actionTypeServantRef;

	public void init(String[] args) {
		try {
			orb = ORB.init(args, null);
			objRef = orb
					.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			ncRef = NamingContextHelper.narrow(objRef);
			NameComponent ncAdmin = new NameComponent(ServerConstants._REF_ADMINISTRATION_SERVANT,"");
			NameComponent ncActionType = new NameComponent(ServerConstants._REF_ACTION_SERVANT, "");
			paths = new NameComponent[] { ncAdmin, ncActionType };
			// servants
			administrationServantRef = AdministrationServantHelper.narrow(ncRef.resolve(paths));
			actionTypeServantRef = ActionTypeServantHelper.narrow(ncRef.resolve(paths));
			
			System.out.println("administrationServantRef @ "+administrationServantRef);
			System.out.println("actionTypeServantRef @ "+actionTypeServantRef);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ORB getOrb() {
		return orb;
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

	public org.omg.CORBA.Object getObjRef() {
		return objRef;
	}

	public void setObjRef(org.omg.CORBA.Object objRef) {
		this.objRef = objRef;
	}

	public NamingContext getNcRef() {
		return ncRef;
	}

	public void setNcRef(NamingContext ncRef) {
		this.ncRef = ncRef;
	}

	public NameComponent[] getPaths() {
		return paths;
	}

	public void setPaths(NameComponent[] paths) {
		this.paths = paths;
	}

	public AdministrationServant getAdministrationServantRef() {
		return administrationServantRef;
	}

	public void setAdministrationServantRef(
			AdministrationServant administrationServantRef) {
		this.administrationServantRef = administrationServantRef;
	}

	public ActionTypeServant getActionTypeServantRef() {
		return actionTypeServantRef;
	}

	public void setActionTypeServantRef(ActionTypeServant actionTypeServantRef) {
		this.actionTypeServantRef = actionTypeServantRef;
	}

}
