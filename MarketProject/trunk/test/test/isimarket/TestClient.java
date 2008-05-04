package test.isimarket;

import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.actiontypeservant.ActionTypeServantHelper;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.server.ServerConstants;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class TestClient {

	private ORB orb;
	private org.omg.CORBA.Object objRef;
	private NamingContext ncRef;
	private NameComponent paths[];
	private AdministrationServant administrationServant;
	private ActionTypeServant actionTypeServant;

	public void init(String[] args) {
		try {
			orb = ORB.init(args, null);
			objRef = orb
					.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
			ncRef = NamingContextHelper.narrow(objRef);
			NameComponent ncAdmin = new NameComponent(ServerConstants._REF_ADMINISTRATION_SERVANT,"");
			NameComponent ncActionType = new NameComponent(ServerConstants._REF_ACTIONTYPE_SERVANT, "");
			paths = new NameComponent[] { ncAdmin, ncActionType };
			// servants
			administrationServant = AdministrationServantHelper.narrow(ncRef.resolve(paths));
			actionTypeServant = ActionTypeServantHelper.narrow(ncRef.resolve(paths));
			
			System.out.println("administrationServant @ "+administrationServant);
			System.out.println("actionTypeServant @ "+actionTypeServant);

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

	public AdministrationServant getAdministrationServant() {
		return administrationServant;
	}

	public void setAdministrationServant(
			AdministrationServant administrationServant) {
		this.administrationServant = administrationServant;
	}

	public ActionTypeServant getActionTypeServant() {
		return actionTypeServant;
	}

	public void setActionTypeServantRef(ActionTypeServant actionTypeServant) {
		this.actionTypeServant = actionTypeServant;
	}

}
