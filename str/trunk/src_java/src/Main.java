import gui.MainFrame;
import network.NetworkManager;


public class Main {
	
	public static void main(String args[]){
		MainFrame.startGui();
		NetworkManager nm = new NetworkManager();
		nm.sendMessage("test");
	}

}
