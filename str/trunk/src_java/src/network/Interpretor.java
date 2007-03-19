package network;

import java.util.LinkedList;
import java.util.Queue;

import controler.ClientControler;

import model.CartesianPosition;
import model.Line;

public class Interpretor {

	private static Interpretor interpretor;
	
	private Queue<String> messagesFromNetwork;
	
	/**
	 * 
	 *
	 */
	private Interpretor() {
		this.messagesFromNetwork = new LinkedList<String>();
	}

	/**
	 * To obliged the unicity of the Interpretor instance.
	 * 
	 * @return
	 */
	public static Interpretor getInstance() {
		if (interpretor == null)
			interpretor = new Interpretor();
		return interpretor;
	}
	
	/**
	 * 
	 * @param cmds
	 */
	public void networkCommandTreatment() {
		Queue<CartesianPosition> dest = ClientControler.getInstance().getCartesianPositionQueue();
		while (!this.getMessagesFromNetwork().isEmpty()) {
			String s = this.getMessagesFromNetwork().remove();
			String tmp = s;
			if (s.contains("@position")) {
				//TODO a faire
			}
		}
	}

	/**
	 * Envoi un ordre de creation d'arret de bus au centre 
	 */
	public void sendCreateBusStop(String _n, Line _l) {
		String tmp = "@createBusStop:" + _n + "," + _l.getNumber() + ";";
		System.out.println("creation de la commande : "+tmp);
		NetworkManager.getInstance().sendMessage(tmp);
	}
	
	/**
	 * Envoi un ordre de creation de bus au centre
	 */
	public void sendCreateBus(String _n, Line _l) {
		String tmp = "@createBus:" + _n + "," + _l.getNumber() + ";";
		System.out.println("creation de la commande : "+tmp);
		NetworkManager.getInstance().sendMessage(tmp);
	}
	
	/**
	 * Envoi un ordre de creation de ligne au centre
	 */
	public void sendCreateLine(String _n) {
		String tmp = "@createLine:" + _n + ";";
		System.out.println("creation de la commande : "+tmp);
		NetworkManager.getInstance().sendMessage(tmp);
	}
	
	/**
	 * Reception d'un message
	 */
	public void receiveMessage(String _str) {
		String tmp = _str;
		try {
			if (tmp.length() > 0) {
				int deb = tmp.indexOf('@');
				int fin = tmp.indexOf(';');
				this.messagesFromNetwork.offer(tmp.substring(deb , fin));
				tmp = tmp.substring(fin + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the messagesFromNetwork
	 */
	public Queue<String> getMessagesFromNetwork() {
		return messagesFromNetwork;
	}

	/**
	 * @param messagesFromNetwork the messagesFromNetwork to set
	 */
	public void setMessagesFromNetwork(Queue<String> _messagesFromNetwork) {
		this.messagesFromNetwork = _messagesFromNetwork;
	}
		
}
