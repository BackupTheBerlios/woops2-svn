package gui.sprite.entities;

import gui.BusDisplayerFrame;


/**
 * Representation graphique d'un Bus
 * @author garwind
 *
 */
public class BusEntity extends Entity{
	
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
	/*
	public void move(long delta) {
		// if we have reached the left hand side of the screen and
		// are moving left then request a logic update 
		if ((dx < 0) && (x < 10)) {
			this.doLogic();
		}
		// and vice vesa, if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > BusDisplayerFrame.X_WINDOW)) {
			this.doLogic();
		}
		
		// proceed with normal move
		super.move(delta);
	}*/

}
