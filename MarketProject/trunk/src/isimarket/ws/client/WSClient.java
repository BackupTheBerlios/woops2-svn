package isimarket.ws.client;

public class WSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Thread rateManager = new Thread() {
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
		rateManager.start();

	}

}
