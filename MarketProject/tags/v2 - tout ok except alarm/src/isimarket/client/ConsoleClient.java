package isimarket.client;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.actiontypeservant.ActionTypeServantHelper;
import isimarket.servants.administrationservant.AdministrationServant;
import isimarket.servants.administrationservant.AdministrationServantHelper;
import isimarket.servants.alarmservant.AlarmServant;
import isimarket.servants.alarmservant.AlarmServantHelper;
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

import com.sun.corba.se.spi.orbutil.fsm.Action;


public class ConsoleClient {

	
	public static void main(String[] args) {
        try {
        	CorbaClient client = new CorbaClient();
        	client.startClient();
        	
			// TODO creer un operator test
			System.out.println("createOperator ... ");
			client.getAdministrationServantRef().createOperator("test","password",1000.0f);
			System.out.println("updateCash ... ");
			//client.getAdministrationServantRef().updateCash("test",3000.0f);
			System.out.println("createNewActionType ... ");
			//actionTypeServantRef.createNewActionType("tst4","action_de_test4","0000-00-00 00:00",10.0f,1000,10.0f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
