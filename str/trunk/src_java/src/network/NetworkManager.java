package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkManager {

	private static final int COMMAND_PORT = 3390;

	private static final int DATA_PORT = 3391;

	private static final String hostToConnect = "localhost";

	private Interpretor interpretor;

	private ServerSocket dataSocket;

	private Socket commandSocket;

	public NetworkManager() {
		try {

			this.dataSocket = new ServerSocket(DATA_PORT);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true)
			try {
				this.commandSocket = new Socket(hostToConnect, COMMAND_PORT);
				break;
			} catch (UnknownHostException ex) {
			} catch (IOException ex) {
			}

		Thread thread = new Thread() {
			public void run() {
				listen();
			}
		};
		thread.start();
	}
	
	public void sendMessage(String _buffer) {
		System.err.println("commandSocket send : " + _buffer);
		try {
			PrintWriter printWriter = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(
							this.commandSocket.getOutputStream())), true);
			printWriter.println(_buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		try {
			Socket acceptedDataSocket = this.dataSocket.accept();
			System.out.println("Socket Acccepted = " + acceptedDataSocket);

			BufferedReader inputDataStream;
			inputDataStream = new BufferedReader(new InputStreamReader(
					acceptedDataSocket.getInputStream()));
			String str;

			while (acceptedDataSocket.isConnected()) {
				str = inputDataStream.readLine();
				if (str == null)
					break;
				System.out.println("received : " + str);
				try {
					Thread.sleep(2);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				str = "test";
				this.sendMessage(str);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Interpretor getInterpretor() {
		return interpretor;
	}

	public void setInterpretor(Interpretor interpretor) {
		this.interpretor = interpretor;
	}
}
