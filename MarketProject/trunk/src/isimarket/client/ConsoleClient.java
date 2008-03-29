package isimarket.client;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;


public class ConsoleClient {

	
	public static void main(String[] args) {
		// create and initialize the ORB
        try {
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
//			NameComponent nc = new NameComponent("ExactTime", "");
//			NameComponent path[] = {nc};
//			ExactTime exactTimeRef = ExactTimeHelper.narrow(ncRef.resolve(path));
//			String time = exactTimeRef.getTime();
//			System.out.println("time => "+time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
