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
    
    private static final int portForServer = 3390;
    
    private static final int portForClient = 3391;
    
    private static final String hostToConnect = "localhost";
    
    private Interpretor interpretor;
    
    private ServerSocket serverSocket;
    
    private Socket clientSocket;
    
    public NetworkManager() {
        try {
            this.serverSocket = new ServerSocket(portForServer);
            this.clientSocket = new Socket(hostToConnect, portForClient);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(){
            public void run(){
                listen();
            }
        };
        thread.start();
    }
    
    private void listen() {
        try {
            Socket acceptedSocket = this.serverSocket.accept();
            System.out.println("SOCKET = " + acceptedSocket);
            BufferedReader fluxEntree;
            
            fluxEntree = new BufferedReader(new InputStreamReader(
                    acceptedSocket.getInputStream()));
            String str;
            
            while (acceptedSocket.isConnected()) {
                str = fluxEntree.readLine();
                if (str == null)
                    break;
                System.out.println("received : " + str); // trace locale
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void sendMessage(String _buffer) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(this.clientSocket.getOutputStream())), true);
            printWriter.println(_buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @return the interpretor
     */
    public Interpretor getInterpretor() {
        return interpretor;
    }
    
    /**
     * @param interpretor
     *            the interpretor to set
     */
    public void setInterpretor(Interpretor interpretor) {
        this.interpretor = interpretor;
    }
    
}
