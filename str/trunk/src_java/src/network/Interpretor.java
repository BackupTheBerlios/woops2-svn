package network;

import java.util.LinkedList;
import java.util.Queue;

import model.Bus;
import model.BusStop;
import model.CartesianPosition;
import model.Line;
import controler.ClientControler;

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
			if (s.contains("@position")) {
				dest.offer(this.positionTreatment(s));
			}
		}
		System.out.println("networkCommandTreatment sleep ...");
	}
	
	/**
	 * 
	 * @param _s
	 * @return
	 */
	public CartesianPosition positionTreatment(String _s) {
		System.out.println("positionTreatment : file mesg CC :"+ClientControler.getInstance().getCartesianPositionQueue().size());
		String[] portions = _s.split(",");
		Line l = ClientControler.getInstance().getLines().get(new Integer(portions[0].substring(portions[0].indexOf(":") + 1)));
		Bus b = ClientControler.getInstance().getBus().get(new Integer(portions[1]));
		BusStop pred = ClientControler.getInstance().getBusStops().get(new Integer(portions[2]));
		BusStop next = l.nextBusStop(pred);
		int pourcentage = new Integer(portions[3]);
		int x = pred.getRepresentation().getX() + ((next.getRepresentation().getX() - pred.getRepresentation().getX()) * pourcentage / 100);
		int y = pred.getRepresentation().getY() + ((next.getRepresentation().getY() - pred.getRepresentation().getY()) * pourcentage / 100);
		System.out.println("nouvel coord b="+b.getId()+" x:"+x+" y:"+y);
		return new CartesianPosition(l, b, x, y);
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
				System.out.println("receiveMessage file mesg net :"+this.messagesFromNetwork.size());
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
