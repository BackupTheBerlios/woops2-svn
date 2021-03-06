package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sebastien
 * 
 */
public class Line extends SystemObject {

	private int number;
	
	private List<BusStop> busStops;
	
	/**
	 * 
	 * @param n
	 */
	public Line(int n) {
		this.number = n;
		this.busStops = new ArrayList<BusStop>();
	}
	
	/**
	 * 
	 * @param bs
	 */
	public void addBusStop(BusStop bs) {
		this.getBusStops().add(bs);
		bs.getLines().add(this);
	}
	
	/**
	 * 
	 * @param bs
	 */
	public void removeBusStop(BusStop bs) {
		bs.getLines().remove(this);
		this.getBusStops().remove(bs);
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public BusStop nextBusStop(BusStop _bs) {
		int i = 0;
		while (i < this.busStops.size() - 1) {
			if (this.busStops.get(i).getId() == _bs.getId()) return this.busStops.get(i + 1);
			i++;
		}
		return this.busStops.get(i);
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the busStops
	 */
	public List<BusStop> getBusStops() {
		return busStops;
	}

	/**
	 * @param busStops the busStops to set
	 */
	public void setBusStops(List<BusStop> busStops) {
		this.busStops = busStops;
	}
}
