package gui.sprite.entities;



/**
 * Representation graphique d'un Bus
 * @author garwind
 *
 */
public class BusEntity extends Entity {
	
	private double moveSpeed = 30;
	
	public BusEntity( String ref, int x, int y) {
		super(ref, x, y);
		
		dx = -moveSpeed;
	}
	
	/**
	 * Collision detected
	 */
	public void collidedWith(Entity other) {
		// TODO Notifier le systeme
	}
	
	public void doLogic() {
		// swap over horizontal movement and move down the
		// screen a bit
		dx = -dx;
		y += 5;
		
	}
	

}
