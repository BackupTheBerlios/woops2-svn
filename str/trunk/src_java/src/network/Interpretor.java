package network;

import java.util.LinkedList;
import java.util.Queue;

import model.Line;

public class Interpretor {

	private static Interpretor interpretor;
	
	private Queue<String> messagesFromNetwork;
	
	private Queue<String> messagesFromInterface;
	
	/**
	 * 
	 *
	 */
	private Interpretor() {
		this.messagesFromNetwork = new LinkedList<String>();
		this.messagesFromInterface = new LinkedList<String>();
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
	 * Envoi un ordre de creation d'arret de bus au centre 
	 */
	public void sendCreateBusStop(String _n, Line _l) {
		String tmp = "@createBusStop:" + _n + "," + _l.getNumber() + ";";
		System.out.println("creation de la commande : "+tmp);
		this.messagesFromInterface.offer(tmp);
	}
	
	/**
	 * Envoi un ordre de creation de bus au centre
	 */
	public void sendCreateBus(String _n, Line _l) {
		String tmp = "@createBus:" + _n + "," + _l.getNumber() + ";";
		System.out.println("creation de la commande : "+tmp);
		this.messagesFromInterface.offer(tmp);
	}
	
	/**
	 * Envoi un ordre de creation de ligne au centre
	 */
	public void sendCreateLine(String _n) {
		String tmp = "@createLine:" + _n + ";";
		System.out.println("creation de la commande : "+tmp);
		this.messagesFromInterface.offer(tmp);
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
	public void setMessagesFromNetwork(Queue<String> messagesFromNetwork) {
		this.messagesFromNetwork = messagesFromNetwork;
	}

	/**
	 * @return the messagesFromInterface
	 */
	public Queue<String> getMessagesFromInterface() {
		return this.messagesFromInterface;
	}

	/**
	 * @param _messagesFromInterface the messagesFromInterface to set
	 */
	public void setMessagesFromInterface(Queue<String> _messagesFromInterface) {
		this.messagesFromInterface = _messagesFromInterface;
	}
	
}
