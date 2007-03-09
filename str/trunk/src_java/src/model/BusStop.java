/**
 * 
 */
package model;

/**
 * @author Sebastien
 *
 */
public class BusStop implements SystemObject {
	
	private int id;
	
	private Position position;
	
	public BusStop(int _id, Position _p) {
		this.id = _id;
		this.position = _p;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
}
