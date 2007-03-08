package controler;

import gui.MainFrame;
import network.Interpretor;

public class ClientControler {
	
	private MainFrame mainFrame;

	public ClientControler() {
		//None.
	}

	public void startClient() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			}
		});
		// test d'envoi
		Interpretor.getInstance().sendBuffer("@Initialize:1;");
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
