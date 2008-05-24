package isimarket.ws.client;

import isimarket.model.ActionType;

import java.rmi.RemoteException;
import java.util.Random;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WSClient {

	private static Call updateActionTypeRateCall;
	private static Call createHistoryLineCall;
	private static Call createActionTypeCall;
	private static Call getActionTypeListCall;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String endpoint = "http://localhost:8080/axis/WSServer.jws";
			Service service = new Service();

			updateActionTypeRateCall = (Call) service.createCall();
			updateActionTypeRateCall.setTargetEndpointAddress(new java.net.URL(
					endpoint));
			updateActionTypeRateCall.setOperationName(new QName(
					"http://soapinterop.org/", "updateActionTypeRate"));

			createHistoryLineCall = (Call) service.createCall();
			createHistoryLineCall.setTargetEndpointAddress(new java.net.URL(
					endpoint));
			createHistoryLineCall.setOperationName(new QName(
					"http://soapinterop.org/", "createHistoryLine"));

			createActionTypeCall = (Call) service.createCall();
			createActionTypeCall.setTargetEndpointAddress(new java.net.URL(
					endpoint));
			createActionTypeCall.setOperationName(new QName(
					"http://soapinterop.org/", "createActionType"));

			getActionTypeListCall = (Call) service.createCall();
			getActionTypeListCall.setTargetEndpointAddress(new java.net.URL(
					endpoint));
			getActionTypeListCall.setOperationName(new QName(
					"http://soapinterop.org/", "getActionTypeList"));

		} catch (Exception e) {
			System.err.println(e.toString());
		}

		Thread rateManager = new Thread() {
			public void run() {
				while (true) {
					try {
						ActionType[] ats = (ActionType[]) WSClient.this.getActionTypeListCall
								.invoke(new Object[0]);
						int i = 0;
						while (i < ats.length) {
							WSClient.this.updateActionTypeRateCall
									.invoke(new Object[] { ats[i],
											WSClient.generateRate() });
						}
						ats = null;
						System.out.println("rateManagerOK");
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
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
						ActionType[] ats = (ActionType[]) WSClient.this.getActionTypeListCall
								.invoke(new Object[0]);
						int i = 0;
						while (i < ats.length) {
							WSClient.this.createHistoryLineCall
									.invoke(new Object[] { ats[i] });
						}
						ats = null;
						System.out.println("eventManagerOK");
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		eventManager.start();
	}

	private static Float generateRate() {
		Random valueGenerator = new Random();
		Random signGenerator = new Random();
		if (signGenerator.nextBoolean())
			return valueGenerator.nextInt(10) * 1.0f;
		else
			return valueGenerator.nextInt(10) * -1.0f;
	}

}
