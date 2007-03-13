package controler;

import gui.MainFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Bus;
import model.BusStop;
import model.Line;
import model.Position;
import network.Interpretor;
import network.NetworkManager;

import common.Constante;

public class ClientControler {

	private static ClientControler clientControler;
	
	private List<BusStop> busStops = new ArrayList<BusStop>();
	
	private List<Bus> bus = new ArrayList<Bus>();
	
	private HashMap<Integer,Line> lines = new HashMap<Integer, Line>();

	/**
	 * 
	 *
	 */
	private ClientControler() {}

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
		this.sendCreateCommand(Constante.LIGNE, "24", "null");
		this.sendCreateCommand(Constante.BUS_STOP, "1", "24");
		this.sendCreateCommand(Constante.BUS_STOP, "3", "24");
		this.sendCreateCommand(Constante.BUS_STOP, "5", "24");
		this.sendCreateCommand(Constante.BUS, "45", "24");
		this.sendCreateCommand(Constante.BUS, "98", "24");
	}

	/**
	 * fonction de creation universelle de commande "create"
	 * @param _code
	 * @param _str
	 */
	public void sendCreateCommand(int _code, String _id, String _l) {
		Line l = null;
		int id = new Integer(_id).intValue();
		if (!_l.equals("null")) {
			l = this.lines.get(new Integer(_l).intValue());
		}
		switch (_code) {
			case Constante.BUS_STOP :
				this.busStops.add(this.createBusStop(new Integer(_id).intValue(), l));
				Interpretor.getInstance().sendCreateBusStop(_id, l);
				break;
			case Constante.LIGNE :
				this.lines.put(id, this.createLine(id));
				Interpretor.getInstance().sendCreateLine(_id);
				break;
			case Constante.BUS :
				this.bus.add(this.createBus(id, l));
				Interpretor.getInstance().sendCreateBus(_id, l);
				break;		
			default : break;
		}
	}

	/**
	 * 
	 *
	 */
	public void startClient() {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame mainFrame = MainFrame.getInstance();
				mainFrame.setVisible(true);
			}
		});
		
		 NetworkManager.getInstance();
		 
	}

	/**
	 * Permet de creer un arret de bus
	 * @param _id id de l arret
	 * @param _l ligne de l'arret
	 * @return un arret de bus
	 */
	private BusStop createBusStop(int _id, Line _l) {
		BusStop tmp = new BusStop(_id);
		tmp.addLine(_l);
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
	private Bus createBus(int _id, Line _l) {
		Bus tmp = new Bus(_id, _l);
		return tmp;
	}
	
}
