package model;

public class CartesianPosition extends SystemObject {

	private Line line;
	
	private Bus bus;
	
	private int x;
	
	private int y;
	
	private int speed;
	
	public CartesianPosition(Line _l, Bus _b, int _x, int _y, int _s) {
		this.line = _l;
		this.bus = _b;
		this.x = _x;
		this.y = _y;
		this.speed = _s;
	}

	/**
	 * @return the bus
	 */
	public Bus getBus() {
		return bus;
	}

	/**
	 * @param bus the bus to set
	 */
	public void setBus(Bus bus) {
		this.bus = bus;
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
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int _speed) {
		this.speed = _speed;
	}
}
