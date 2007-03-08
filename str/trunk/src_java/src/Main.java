import controler.ClientControler;
import gui.MainFrame;
import network.NetworkManager;


public class Main {
	
	public static void main(String args[]){
		ClientControler c = new ClientControler();
		c.startClient();
	}

}
