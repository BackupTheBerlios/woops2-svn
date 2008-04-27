package isimarket.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerConstants {
	public static final String _NAMING_SERVICE_NAME = "NameService";
	public static final String _REF_WALLET_SERVANT = "WalletServant";
	public static final String _REF_ALARM_SERVANT = "AlarmServant";
	public static final String _REF_ADMINISTRATION_SERVANT = "AdministrationServant";
	public static final String _REF_ACTION_SERVANT = "ActionServant";
	
	/**
	 * Gestion de format des dates
	 */
	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	/**
	 * Retourne la date courante
	 * @return
	 */
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    return sdf.format(cal.getTime());

	  }
}
