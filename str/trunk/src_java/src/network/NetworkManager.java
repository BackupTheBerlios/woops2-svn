package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkManager {

	private static final int COMMAND_PORT = 3390;

	//private static final int DATA_PORT = 3391;

	private static final String hostToConnect = "localhost";

	//private ServerSocket dataSocket;

	private Socket commandSocket;

	private static NetworkManager networkManager;

	/**
	 * To the send a message through the network.
	 * 
	 * @param _buffer
	 */
	public void sendMessage(String _buffer) {
		try {
			PrintWriter printWriter = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(
							this.commandSocket.getOutputStream())), true);
			printWriter.println(_buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To obliged the unicity of the NetworkManager instance.
	 * 
	 * @return
	 */
	public static NetworkManager getInstance() {
		if (networkManager == null)
			networkManager = new NetworkManager();
		return networkManager;
	}

	/**
	 * Private constructor for the Singleton.
	 * @throws  
	 * 
	 */
	private NetworkManager() {
		System.out.println("creation NM");
		
		while (true)
			try {
				this.commandSocket = new Socket(hostToConnect, COMMAND_PORT);
				//Thread.sleep(100);
				break;
			} catch (UnknownHostException ex) {
				// None.
			} catch (IOException ex) {
				// None.
			}/* catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		Thread thread = new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++){
					listen();
				}
				System.out.println("fin listen");
			}
		};
		thread.start();
	}

	/**
	 * For the listen of the DataSocket.
	 * 
	 */
	private void listen() {
		System.out.println("listening ...");
		try {
			//Socket acceptedDataSocket = this.dataSocket.accept();
			//System.out.println("accepted connection");
			BufferedReader inputDataStream;
			inputDataStream = new BufferedReader(new InputStreamReader(
					commandSocket.getInputStream()));
			String str;

			while (true) {
				System.out.println("getting buffer ... ");
				str = inputDataStream.readLine();
				System.out.println("received buffer = "+str);
				if (str == null)
					break;
				//Interpretor.getInstance().receiveMessage(str);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
