package gui.sprite.entities;


/**
 * Representation graphique d'un Bus
 * @author garwind
 *
 */
public class BusEntity extends Entity{
	
	
	public BusEntity( String ref, int x, int y) {
		super(ref, x, y);
	}
	
	/**
	 * Collision detected
	 */
	public void collidedWith(Entity other) {
		// TODO Notifier le systeme
	}

}
