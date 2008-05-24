package isimarket.ws.client;

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
			String endpoint = "http://localhost:8080/axis/services";
			Service service = new Service();
			
			updateActionTypeRateCall = (Call) service.createCall();
			updateActionTypeRateCall.setTargetEndpointAddress(new java.net.URL(endpoint));
			updateActionTypeRateCall.setOperationName(new QName("http://soapinterop.org/",
					"updateActionTypeRate"));
			
			createHistoryLineCall = (Call) service.createCall();
			createHistoryLineCall.setTargetEndpointAddress(new java.net.URL(endpoint));
			createHistoryLineCall.setOperationName(new QName("http://soapinterop.org/",
				"createHistoryLine"));
			
			createActionTypeCall = (Call) service.createCall();
			createActionTypeCall.setTargetEndpointAddress(new java.net.URL(endpoint));
			createActionTypeCall.setOperationName(new QName("http://soapinterop.org/",
				"createActionType"));
			
			getActionTypeListCall = (Call) service.createCall();
			getActionTypeListCall.setTargetEndpointAddress(new java.net.URL(endpoint));
			getActionTypeListCall.setOperationName(new QName("http://soapinterop.org/",
				"getActionTypeList"));

			//String ret = (String) call.invoke(new Object[] { "Hello!" });

			//System.out.println("Sent 'Hello!', got '" + ret + "'");
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		Thread rateManager = new Thread() {
			public void run() {
				while (true) {
					//ActionType[] ats = (ActionType[]) WSClient.this.getActionTypeListCall.invoke(new Object[0]);
					// while passer ts les actiontypes						
						try {
							//WSClient.this.updateActionTypeRateCall.invoke(new Object[] { , WSClient.generateRate()});
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
					// TODO appel du webservice
					try {

						Thread.sleep(300000);
					} catch (InterruptedException e) {
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
