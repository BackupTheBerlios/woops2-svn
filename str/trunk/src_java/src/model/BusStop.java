/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastien
 *
 */
public class BusStop extends SystemObject {
	
	private int id;
	
	private Position position;
	
	private List<Line> lines;
	
	/**
	 * 
	 * @param _id
	 * @param _p
	 */
	public BusStop(int _id, Position _p) {
		this.id = _id;
		this.position = _p;
		this.lines = new ArrayList<Line>();
	}
	
	/**
	 * 
	 * @param l
	 */
	public void addLine(Line l) {
		this.getLines().add(l);
		l.getBusStops().add(this);
	}
	
	/**
	 * 
	 * @param l
	 */
	public void removeLine(Line l) {
		l.getBusStops().remove(this);
		this.getLines().remove(l);
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

	/**
	 * @return the lines
	 */
	public List<Line> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
}
