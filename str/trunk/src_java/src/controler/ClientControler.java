package controler;

import gui.BusDisplayerFrame;
import gui.MainFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Bus;
import model.BusStop;
import model.Line;
import network.Interpretor;
import network.NetworkManager;

import common.Constante;

public class ClientControler {

	private static ClientControler clientControler;

	private List<BusStop> busStops = new ArrayList<BusStop>();

	private List<Bus> bus = new ArrayList<Bus>();

	private HashMap<Integer, Line> lines = new HashMap<Integer, Line>();
	
	private Queue<String> messagesFromInterface;

	/**
	 * 
	 *
	 */
	private ClientControler() {
		this.messagesFromInterface = new LinkedList<String>();
	}

	/**
	 * 
	 * @return
	 */
	public static ClientControler getInstance() {
		if (clientControler == null)
			clientControler = new ClientControler();
		return clientControler;
	}

	public void initialisation() {
		this.sendCreateCommand(Constante.LIGNE, "12", "null", 0, 0);
		this.sendCreateCommand(Constante.BUS_STOP, "1", "12", 104, 37);
		this.sendCreateCommand(Constante.BUS_STOP, "2", "12", 110, 69);
		this.sendCreateCommand(Constante.BUS_STOP, "3", "12", 160, 75);
		this.sendCreateCommand(Constante.BUS_STOP, "4", "12", 209, 45);
		this.sendCreateCommand(Constante.BUS_STOP, "5", "12", 309, 79);
		this.sendCreateCommand(Constante.BUS_STOP, "6", "12", 383, 84);
		this.sendCreateCommand(Constante.BUS_STOP, "7", "12", 438, 83);
		this.sendCreateCommand(Constante.BUS_STOP, "8", "12", 546, 28);
		this.sendCreateCommand(Constante.BUS_STOP, "9", "12", 603, 44);
		this.sendCreateCommand(Constante.BUS_STOP, "10", "12", 689, 121);
		this.sendCreateCommand(Constante.BUS_STOP, "11", "12", 755, 181);
		this.sendCreateCommand(Constante.BUS_STOP, "12", "12", 774, 243);
		this.sendCreateCommand(Constante.BUS_STOP, "13", "12", 816, 230);
		this.sendCreateCommand(Constante.BUS, "45", "12", 104, 37);
		this.sendCreateCommand(Constante.BUS, "98", "12", 104, 37);
	}

	/**
	 * fonction de creation universelle de commande "create"
	 * @param _code
	 * @param _str
	 */
	public void sendCreateCommand(int _code, String _id, String _l, int _x, int _y) {
		Line l = null;
		int id = new Integer(_id).intValue();
		if (!_l.equals("null")) {
			l = this.lines.get(new Integer(_l).intValue());
		}
		switch (_code) {
			case Constante.BUS_STOP:
				this.busStops.add(this
					.createBusStop(new Integer(_id).intValue(), l, _x, _y));
				Interpretor.getInstance().sendCreateBusStop(_id, l);
				break;
			case Constante.LIGNE:
				this.lines.put(id, this.createLine(id));
				Interpretor.getInstance().sendCreateLine(_id);
				break;
			case Constante.BUS:
				this.bus.add(this.createBus(id, l, _x, _y));
				Interpretor.getInstance().sendCreateBus(_id, l);
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 * @param cmds
	 */
	public void networkCommandTreatment() {
		Queue<String> q = Interpretor.getInstance().getMessagesFromNetwork();
		while (!q.isEmpty()) {
			String s = q.remove();
			System.out.println("Taille de la file network : " + q.size());
			System.out.println(s);
		}
	}
	
	/**
	 * 
	 * @param cmds
	 */
	public void interfaceCommandTreatment() {
		Queue<String> q = this.getMessagesFromInterface();
		while (!q.isEmpty()) {
			String s = q.remove();
			System.out.println("Taille de la file interface : " + q.size());
			System.out.println("envoi de la commande : "+s);
			NetworkManager.getInstance().sendMessage(s);
		}
	}

	/**
	 * 
	 *
	 */
	public void startClient() {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame.getInstance().setVisible(true);
			}
		});

		Thread threadDisplayer = new Thread() {
			public void run() {
				BusDisplayerFrame.getInstance().mainLoop();
			}
		};
		threadDisplayer.start();

		NetworkManager.getInstance();

		Thread threadNetworkMessages = new Thread() {
			public void run() {
				while (true) {
					networkCommandTreatment();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		threadNetworkMessages.start();
		
		Thread threadInterfaceMessages = new Thread() {
			public void run() {
				while (true) {
					interfaceCommandTreatment();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		threadInterfaceMessages.start();

	}

	/**
	 * Permet de creer un arret de bus
	 * @param _id id de l arret
	 * @param _l ligne de l'arret
	 * @return un arret de bus
	 */
	private BusStop createBusStop(int _id, Line _l, int _x, int _y) {
		BusStop tmp = new BusStop(_id, _x, _y);
		tmp.addLine(_l);
		BusDisplayerFrame.getInstance().getEntities().add(tmp.getRepresentation());
		return tmp;
	}

	/**
	 * Permet de creer une ligne
	 * @param _n numero de la ligne
	 * @return une ligne
	 */
	private Line createLine(int _n) {
		Line tmp = new Line(_n);
		return tmp;
	}

	/**
	 * Permet de creer un Bus
	 * @param _id id du bus
	 * @param _l ligne du bus
	 * @return un bus
	 */
	private Bus createBus(int _id, Line _l, int _x, int _y) {
		Bus tmp = new Bus(_id, _l, _x, _y);
		BusDisplayerFrame.getInstance().getEntities().add(tmp.getRepresentation());
		return tmp;
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
