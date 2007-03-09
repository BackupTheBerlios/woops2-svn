import controler.ClientControler;


public class Main {
	
	public static void main(String args[]){
		ClientControler c = ClientControler.getInstance();
		c.startClient();
	}

}
