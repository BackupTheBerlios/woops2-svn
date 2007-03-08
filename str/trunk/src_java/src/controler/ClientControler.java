package controler;

import gui.MainFrame;
import network.Interpretor;

public class ClientControler {
	
	private static ClientControler clientControler;
	
	private MainFrame mainFrame;

	private ClientControler() {
		
	}
	
	public static ClientControler getInstance(){
		if (clientControler == null) clientControler = new ClientControler();
		return clientControler;
	}
	
	public void sendMessage(){
		Interpretor.getInstance().sendBuffer("@Initialize:1;");
	}
	
	public void startClient() {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			}
		});
		// test d'envoi
		
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
