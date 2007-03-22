package controler;

import gui.BusDisplayerFrame;
import gui.MainFrame;
import gui.sprite.SpriteFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import model.Bus;
import model.BusStop;
import model.CartesianPosition;
import model.Information;
import model.Line;
import network.Interpretor;
import network.NetworkManager;

import common.Constante;

public class ClientControler implements ListDataListener{

	private static ClientControler clientControler;

	private HashMap<Integer, BusStop> busStops;

	private HashMap<Integer, Bus> bus;

	private HashMap<Integer, Line> lines;

	private Queue<CartesianPosition> cartesianPositionQueue;
	
	private Queue<Information> informationsQueue;
	
	private Queue<String> priorityMessageQueue;

	/**
	 * 
	 * 
	 */
	private ClientControler() {
		this.bus = new HashMap<Integer, Bus>();
		this.busStops = new HashMap<Integer, BusStop>();
		this.lines = new HashMap<Integer, Line>();
		this.cartesianPositionQueue = new LinkedList<CartesianPosition>();
		this.informationsQueue = new LinkedList<Information>();
		this.priorityMessageQueue = new LinkedList<String>();
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
		this.sendCreateCommand(Constante.LIGNE, "24", "null", 0, 0);
		
		this.sendCreateCommand(Constante.BUS_STOP, "1", "12", 104, 37);
		this.sendCreateCommand(Constante.BUS_STOP, "2", "12", 110, 69);
		this.sendCreateCommand(Constante.BUS_STOP, "3", "12", 136, 86);
		this.sendCreateCommand(Constante.BUS_STOP, "4", "12", 209, 45);
		this.sendCreateCommand(Constante.BUS_STOP, "5", "12", 255, 65);
		this.sendCreateCommand(Constante.BUS_STOP, "6", "12", 309, 79);
		this.sendCreateCommand(Constante.BUS_STOP, "7", "12", 383, 84);
		this.sendCreateCommand(Constante.BUS_STOP, "8", "12", 468, 83);
		this.sendCreateCommand(Constante.BUS_STOP, "9", "12", 515, 49);
		this.sendCreateCommand(Constante.BUS_STOP, "10", "12", 570, 18);
		this.sendCreateCommand(Constante.BUS_STOP, "11", "12", 630, 70);
		this.sendCreateCommand(Constante.BUS_STOP, "12", "12", 689, 121);
		this.sendCreateCommand(Constante.BUS_STOP, "13", "12", 755, 181);
		this.sendCreateCommand(Constante.BUS_STOP, "14", "12", 774, 243);
		this.sendCreateCommand(Constante.BUS_STOP, "15", "12", 816, 230);
		
		this.sendCreateCommand(Constante.BUS_STOP, "16", "24", 102, 25);
		this.sendCreateCommand(Constante.BUS_STOP, "17", "24", 75, 75);
		this.sendCreateCommand(Constante.BUS_STOP, "18", "24", 37, 98);
		this.sendCreateCommand(Constante.BUS_STOP, "19", "24", 50, 150);
		this.sendCreateCommand(Constante.BUS_STOP, "20", "24", 115, 162);
		this.sendCreateCommand(Constante.BUS_STOP, "21", "24", 191, 175);
		this.sendCreateCommand(Constante.BUS_STOP, "22", "24", 259, 197);
		this.sendCreateCommand(Constante.BUS_STOP, "23", "24", 321, 208);
		this.sendCreateCommand(Constante.BUS_STOP, "24", "24", 423, 213);
		this.sendCreateCommand(Constante.BUS_STOP, "25", "24", 489, 214);
		this.sendCreateCommand(Constante.BUS_STOP, "26", "24", 577, 212);
		this.sendCreateCommand(Constante.BUS_STOP, "27", "24", 634, 208);
		this.sendCreateCommand(Constante.BUS_STOP, "28", "24", 679, 267);
		this.sendCreateCommand(Constante.BUS_STOP, "29", "24", 719, 306);
		this.sendCreateCommand(Constante.BUS_STOP, "30", "24", 750, 292);
		this.sendCreateCommand(Constante.BUS_STOP, "31", "24", 818, 258);
		
		//this.sendCreateCommand(Constante.BUS, "45", "12", 106, 40);
		//this.sendCreateCommand(Constante.BUS, "98", "24", 104, 29);
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
			case Constante.START_BUS:
				Bus b1 = ClientControler.getInstance().getBus().get(id);
				b1.getRepresentation().setIsRunning(true);
				Interpretor.getInstance().sendStartBus(_id);
				break;
			case Constante.ACC_BUS:
				Interpretor.getInstance().sendAccelerateBus(_id);
				break;
			case Constante.DEC_BUS:
				Interpretor.getInstance().sendDecelerateBus(_id);
				break;
			case Constante.ACCIDENT:
				Interpretor.getInstance().sendAccidentOnBus(_id);
				break;
			case Constante.BREAKDOWN:
				Interpretor.getInstance().sendBreakdownOnBus(_id);
				break;
			case Constante.AGRESSION:
				Interpretor.getInstance().sendAgressionOnBus(_id);
				break;
			default:	
				break;
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
					if (ClientControler.getInstance().informationsQueue.size() > 0) {
						System.out.println("refreshing ...  ["+ClientControler.getInstance().informationsQueue.size()+"]informations");
						MainFrame.getInstance().refreshInformation();
						System.out.println("refreshed");
					}
					if (ClientControler.getInstance().priorityMessageQueue.size() > 0){
						System.out.println("refreshing priorityMessageQueue...  ["+ClientControler.getInstance().priorityMessageQueue.size()+"]priorityMessageQueue");
						MainFrame.getInstance().refreshPriorityMessages();
						System.out.println("refreshed priorityMessageQueue");
					}
					try {
						Thread.sleep(3000);
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
			b.setSpeed(cp.getSpeed());
			b.setState(Constante.NORMAL_STATE);
			b.getRepresentation().setSprite(SpriteFactory.getInstance().getSprite("resources/images/dot_b.png"));
			System.out.println("==> b :"+b.getId()+" x:"+b.getRepresentation().getX()+" y:"+b.getRepresentation().getY());
		}
		// update du modele de la jtable
		if (bus.size() > 0) MainFrame.getInstance().refreshBusTable();
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

	public void contentsChanged(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the informationsQueue
	 */
	public Queue<Information> getInformationsQueue() {
		return this.informationsQueue;
	}

	/**
	 * @param _informationsQueue the informationsQueue to set
	 */
	public void setInformationsQueue(Queue<Information> _informationsQueue) {
		this.informationsQueue = _informationsQueue;
	}

	/**
	 * @return the priorityMessageQueue
	 */
	public Queue<String> getPriorityMessageQueue() {
		return priorityMessageQueue;
	}

	/**
	 * @param priorityMessageQueue the priorityMessageQueue to set
	 */
	public void setPriorityMessageQueue(Queue<String> priorityMessageQueue) {
		this.priorityMessageQueue = priorityMessageQueue;
	}

}
