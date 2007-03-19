package model;

import gui.sprite.entities.BusEntity;

public class Bus extends SystemObject {
	
	private int id;
	
	private Line line;
	
	private BusEntity representation;
	
	/**
	 * 
	 * @param _id
	 */
	public Bus(int _id, Line _l, int _x, int _y) {
		this.id = _id;
		this.line = _l;
		this.representation = new BusEntity("resources/images/dot_b.png", _x, _y);
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
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * @return the representation
	 */
	public BusEntity getRepresentation() {
		return representation;
	}

	/**
	 * @param representation the representation to set
	 */
	public void setRepresentation(BusEntity representation) {
		this.representation = representation;
	}
	
}
