/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sebastien
 *
 */
public class BusStop extends SystemObject {
	
	private int id;
	
	private HashMap<Integer,Position> positions;
	
	private List<Line> lines;
	
	/**
	 * Constructeur par defaut
	 * @param _id
	 * @param _p
	 */
	public BusStop(int _id) {
		this.id = _id;
		this.lines = new ArrayList<Line>();
		positions = new HashMap<Integer, Position>();
	}
	
	/**
	 * Ajoute le BusStop sur une ligne
	 * @param l
	 */
	public void addLine(Line _l) {
		this.getLines().add(_l);
		_l.getBusStops().add(this);
		Position pos = null;
		if (_l.getBusStops().size() == 1) {
			pos = new Position(this, 0f);
		} else {
			pos = new Position(_l.getBusStops().get(_l.getBusStops().size() - 2), 1500f);
		}
		this.getPositions().put(_l.getNumber(), pos);
	}
	
	/**
	 * Supprime le BusStop d'une ligne
	 * @param l
	 */
	public void removeLine(Line _l) {
		_l.getBusStops().remove(this);
		this.getPositions().remove(_l.getNumber());
		this.getLines().remove(_l);
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
	 * @return the positions
	 */
	public HashMap<Integer, Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(HashMap<Integer, Position> positions) {
		this.positions = positions;
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
