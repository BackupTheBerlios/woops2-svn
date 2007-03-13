package gui.sprite.entities;

import gui.sprite.SpriteDisplayerCanvas;

/**
 * Representation graphique d'un Bus
 * @author garwind
 *
 */
public class BusEntity extends Entity{
	
	private SpriteDisplayerCanvas displayer;
	
	public BusEntity(String ref, int x, int y) {
		super(ref, x, y);
		
	}
	
	public void collidedWith(Entity other) {
		// TODO Notifier le systeme
	}

	public SpriteDisplayerCanvas getBusDisplayer() {
		return displayer;
	}

	public void setBusDisplayer(SpriteDisplayerCanvas busDisplayer) {
		this.displayer = busDisplayer;
	}

}
