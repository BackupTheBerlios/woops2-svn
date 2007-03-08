package controler;

import gui.MainFrame;
import network.NetworkManager;

public class ClientControler {
	NetworkManager networkManager;
	MainFrame mainFrame;
	
	public ClientControler(){}
	
	public void startClient(){
		 java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	mainFrame = new MainFrame();
            	mainFrame.setVisible(true);
            }
        });
		networkManager = new NetworkManager();
		// test d'envoi
		networkManager.sendMessage("test");
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	public void setNetworkManager(NetworkManager networkManager) {
		this.networkManager = networkManager;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
