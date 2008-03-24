package isimarket.server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class ISIMarketServer {
	public static void main(String[] args) {
		try{
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
 
            // create servant and register it with the ORB
//            ExactTimeServant exactTimeRef = new ExactTimeServant();
//            orb.connect(exactTimeRef);
 
            // get the root naming context
            org.omg.CORBA.Object objRef = 
                orb.resolve_initial_references(ServerConstants._NAMING_SERVICE_NAME);
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
 
            // bind the Object Reference in Naming
//            NameComponent nc = new NameComponent("ExactTime", "");
//            NameComponent path[] = {nc};
//            ncRef.rebind(path, exactTimeRef);
            
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
}
