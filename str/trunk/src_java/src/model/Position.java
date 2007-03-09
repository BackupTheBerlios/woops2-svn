package model;

public class Position implements SystemObject {
	
	private BusStop predecessor;
	
	private float distance;
	
	public Position(BusStop _bs, float _dis) {
		this.predecessor = _bs;
		this.distance = _dis;
	}

	/**
	 * @return the distance
	 */
	public float getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * @return the predecessor
	 */
	public BusStop getPredecessor() {
		return predecessor;
	}

	/**
	 * @param predecessor the predecessor to set
	 */
	public void setPredecessor(BusStop predecessor) {
		this.predecessor = predecessor;
	}

}
