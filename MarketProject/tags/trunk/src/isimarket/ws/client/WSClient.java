package isimarket.ws.client;

import isimarket.server.ServerConstants;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WSClient {

	static String endpoint = "http://localhost:8080/axis/WSServer.jws";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Thread rateManager = new Thread() {
			public void run() {
				while (true) {
					try {
						String[] codes = WSClient.getActionTypeList();
						for (int i = 0; i < codes.length; i++)
							WSClient.updateActionTypeRate(codes[i],
									generateRate());
						codes = null;
						System.out.println("Mise à jour des cours");
						// temps en ms (base 1 min)
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		rateManager.start();

		Thread eventManager = new Thread() {
			public void run() {
				while (true) {
					try {
						String[] codes = WSClient.getActionTypeList();
						for (int i = 0; i < codes.length; i++)
							WSClient.createHistoryLine(codes[i]);
						codes = null;
						System.out.println("Historique créé");
						// temps en ms (base 5 min)
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		eventManager.start();
	}

	/**
	 * 
	 */
	protected static void updateActionTypeRate(String actionCode, Float rate) {
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://soapinterop.org/",
					"updateActionTypeRate"));

			call.invoke(new Object[] { actionCode, rate });

		} catch (ServiceException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}

	/**
	 * 
	 * @param at
	 */
	protected static void createHistoryLine(String code) {
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://soapinterop.org/",
					"createHistoryLine"));

			call.invoke(new Object[] { code });

		} catch (ServiceException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}

	/**
	 * 
	 * @param code
	 * @param label
	 * @param introductionPrice
	 * @param quantity
	 * @param currentPrice
	 */
	protected static void createActionType(String code, String label,
			Float introductionPrice, Integer quantity) {
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://soapinterop.org/",
					"createActionType"));

			call.invoke(new Object[] {code, label, ServerConstants.now(),
					introductionPrice, quantity, introductionPrice});

		} catch (ServiceException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}

	/**
	 * 
	 * @return
	 */
	protected static String[] getActionTypeList() {
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://soapinterop.org/",
					"getActionTypeList"));
			String[] codes = (String[]) call.invoke(new Object[0]);
			return codes;

		} catch (ServiceException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private static Float generateRate() {
		Random valueGenerator = new Random();
		Random signGenerator = new Random();
		if (signGenerator.nextBoolean())
			return valueGenerator.nextInt(5) * 1.0f;
		else
			return valueGenerator.nextInt(5) * -1.0f;
	}

}
