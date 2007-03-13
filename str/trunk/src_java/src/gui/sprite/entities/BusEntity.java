package gui.sprite.entities;

import gui.sprite.Displayer;

/**
 * Representation graphique d'un Bus
 * @author garwind
 *
 */
public class BusEntity extends Entity{
	
	private Displayer busDisplayer;
	
	public BusEntity(String ref, int x, int y) {
		super(ref, x, y);
		
	}
	
	public void collidedWith(Entity other) {
		// TODO Notifier le systeme
	}

	public Displayer getBusDisplayer() {
		return busDisplayer;
	}

	public void setBusDisplayer(Displayer busDisplayer) {
		this.busDisplayer = busDisplayer;
	}

}
