package model;

public class Bus extends SystemObject {
	
	private int id;
	
	private Line line;
	
	/**
	 * 
	 * @param _id
	 */
	public Bus(int _id, Line _l) {
		this.id = _id;
		this.line = _l;
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
	
}
