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
import model.CartesianPosition;
import model.Line;
import network.Interpretor;
import network.NetworkManager;

import common.Constante;

public class ClientControler {

	private static ClientControler clientControler;

	private HashMap<Integer, BusStop> busStops;

	private HashMap<Integer, Bus> bus;

	private HashMap<Integer, Line> lines;

	private Queue<String> messagesFromInterface;

	private Queue<CartesianPosition> cartesianPositionQueue;

	/**
	 * 
	 * 
	 */
	private ClientControler() {
		this.bus = new HashMap<Integer, Bus>();
		this.busStops = new HashMap<Integer, BusStop>();
		this.lines = new HashMap<Integer, Line>();
		this.messagesFromInterface = new LinkedList<String>();
		this.cartesianPositionQueue = new LinkedList<CartesianPosition>();
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
	 * 
	 * @param _code
	 * @param _str
	 */
	public void sendCreateCommand(int _code, String _id, String _l, int _x,
			int _y) {
		Line l = null;
		int id = new Integer(_id).intValue();
		if (!_l.equals("null")) {
			l = this.lines.get(new Integer(_l).intValue());
		}
		switch (_code) {
		case Constante.BUS_STOP:
			this.busStops.put(id, this.createBusStop(id, l, _x, _y));
			Interpretor.getInstance().sendCreateBusStop(_id, l);
			break;
		case Constante.LIGNE:
			this.lines.put(id, this.createLine(id));
			Interpretor.getInstance().sendCreateLine(_id);
			break;
		case Constante.BUS:
			this.bus.put(id, this.createBus(id, l, _x, _y));
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
	public void interfaceCommandTreatment() {
		Queue<String> q = this.getMessagesFromInterface();
		while (!q.isEmpty()) {
			String s = q.remove();
			System.out.println("Taille de la file interface : " + q.size());
			System.out.println("envoi de la commande : " + s);
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
					Interpretor.getInstance().networkCommandTreatment();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		threadNetworkMessages.start();

		Thread threadCartesianPositionUpdate = new Thread() {
			public void run() {
				while (true) {
					cartesianPositionUpdate();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		threadCartesianPositionUpdate.start();

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
	 * Methode pour traiter les cartesianPosition et mettre a jours le modele
	 */
	private void cartesianPositionUpdate() {
		while (!this.cartesianPositionQueue.isEmpty()) {
			CartesianPosition cp = this.cartesianPositionQueue.remove();
			Bus b = this.bus.get(cp.getBus().getId());
			b.getRepresentation().setX(cp.getX());
			b.getRepresentation().setY(cp.getY());
			System.out.println("==> b :"+b.getId()+" x:"+b.getRepresentation().getX()+" y:"+b.getRepresentation().getY());
		}
	}

	/**
	 * Permet de creer un arret de bus
	 * 
	 * @param _id
	 *            id de l arret
	 * @param _l
	 *            ligne de l'arret
	 * @return un arret de bus
	 */
	private BusStop createBusStop(int _id, Line _l, int _x, int _y) {
		BusStop tmp = new BusStop(_id, _x, _y);
		tmp.addLine(_l);
		BusDisplayerFrame.getInstance().getEntities().add(
				tmp.getRepresentation());
		return tmp;
	}

	/**
	 * Permet de creer une ligne
	 * 
	 * @param _n
	 *            numero de la ligne
	 * @return une ligne
	 */
	private Line createLine(int _n) {
		Line tmp = new Line(_n);
		return tmp;
	}

	/**
	 * Permet de creer un Bus
	 * 
	 * @param _id
	 *            id du bus
	 * @param _l
	 *            ligne du bus
	 * @return un bus
	 */
	private Bus createBus(int _id, Line _l, int _x, int _y) {
		Bus tmp = new Bus(_id, _l, _x, _y);
		BusDisplayerFrame.getInstance().getEntities().add(
				tmp.getRepresentation());
		return tmp;
	}

	/**
	 * @return the messagesFromInterface
	 */
	public Queue<String> getMessagesFromInterface() {
		return this.messagesFromInterface;
	}

	/**
	 * @param _messagesFromInterface
	 *            the messagesFromInterface to set
	 */
	public void setMessagesFromInterface(Queue<String> _messagesFromInterface) {
		this.messagesFromInterface = _messagesFromInterface;
	}

	/**
	 * @return the cartesianPositionQueue
	 */
	public Queue<CartesianPosition> getCartesianPositionQueue() {
		return cartesianPositionQueue;
	}

	/**
	 * @param cartesianPositionQueue
	 *            the cartesianPositionQueue to set
	 */
	public void setCartesianPositionQueue(
			Queue<CartesianPosition> cartesianPositionQueue) {
		this.cartesianPositionQueue = cartesianPositionQueue;
	}

	/**
	 * @return the lines
	 */
	public HashMap<Integer, Line> getLines() {
		return this.lines;
	}

	/**
	 * @param _lines
	 *            the lines to set
	 */
	public void setLines(HashMap<Integer, Line> _lines) {
		this.lines = _lines;
	}

	/**
	 * @return the bus
	 */
	public HashMap<Integer, Bus> getBus() {
		return this.bus;
	}

	/**
	 * @param _bus
	 *            the bus to set
	 */
	public void setBus(HashMap<Integer, Bus> _bus) {
		this.bus = _bus;
	}

	/**
	 * @return the busStops
	 */
	public HashMap<Integer, BusStop> getBusStops() {
		return busStops;
	}

	/**
	 * @param busStops
	 *            the busStops to set
	 */
	public void setBusStops(HashMap<Integer, BusStop> busStops) {
		this.busStops = busStops;
	}

}
