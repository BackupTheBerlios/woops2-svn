package controler;

import gui.DebugFrame;
import gui.MainFrame;

import java.util.ArrayList;
import java.util.List;

import model.BusStop;
import model.Position;
import network.Interpretor;

public class ClientControler {

	private static ClientControler clientControler;
	
	private List<BusStop> busStops = new ArrayList<BusStop>();

	private ClientControler() {
	}

	public static ClientControler getInstance() {
		if (clientControler == null)
			clientControler = new ClientControler();
		return clientControler;
	}

	public void sendCreateCommand(int _code, String _str) {
		switch (_code) {
			case 0 : Interpretor.getInstance().createBusStop(_str);
						  break;
		}
	}

	public void startClient() {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame mainFrame = MainFrame.getInstance();
				mainFrame.setVisible(true);
			}
		});
		
		 java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                 DebugFrame debugFrame = DebugFrame.getInstance();
                 debugFrame.setVisible(true);
             }
         });
		// test d'envoi

	}

	public void createBusStop(int _nbBusStop) {

		if (_nbBusStop > 0) {
			BusStop current = new BusStop(1, null);
			busStops.add(current);
			BusStop tmp = current;
			for (int i = 2; i <= _nbBusStop; i++) {
				Position pos = new Position(tmp, 1500f);
				current = new BusStop(i, pos);
				busStops.add(current);
				tmp = current;
			}
		}
		
		for (BusStop tmp : this.busStops) {
			if (tmp.getPosition() == null) {
				System.out.println("BusStop " + tmp.getId());
			} else {
				System.out.println("BusStop " + tmp.getId()
						+ " -> predecesseur : "
						+ tmp.getPosition().getPredecessor().getId() + " a "
						+ tmp.getPosition().getDistance());
			}
		}
	}

}
