package controler;

import gui.MainFrame;

import java.util.ArrayList;
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
	
	private List<Line> lines = new ArrayList<Line>();

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

	/**
	 * fonction de creation universelle de commande "create"
	 * @param _code
	 * @param _str
	 */
	public void sendCreateCommand(int _code, String _nb, String _l) {
		int nb = new Integer(_nb).intValue();
		switch (_code) {
			case Constante.BUS_STOP :
				Line l = this.lines.get(new Integer(_l) - 1); // attention aux tailles de tableaux !
				for (int i = 1;i <= nb;i++) {
					this.createBusStop(i, l);
				}
				Interpretor.getInstance().createBusStop(_nb);
				break;
			case Constante.LIGNE :
				this.lines.add(this.createLine(new Integer(_nb).intValue()));
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
		 
		 // creation d'une ligne n°1
		 this.sendCreateCommand(Constante.LIGNE,"1",null);
	}

	/**
	 * 
	 * @param _nbBusStop
	 */
	private BusStop createBusStop(int _id, Line _l) {
		BusStop tmp = new BusStop(_id);
		tmp.addLine(_l);
		return tmp;
	}

	/**
	 * Permet de creer une ligne
	 * @param _n
	 */
	private Line createLine(int _n) {
		Line tmp = new Line(_n);
		return tmp;
	}
	
}
