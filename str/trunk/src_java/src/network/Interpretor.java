package network;

public class Interpretor {
	
	private NetworkManager networkManager;
	
	private AbstractSystemObject abstractSystemObject;
	
	public void buildAbstractSystemObject(String _buffer){
		//TODO !
	}
	
	//to the networkmanager
	public void interpreteCurrentAbstractSystemObject(){
		//TODO !
	}

	/**
	 * @return the networkManager
	 */
	public NetworkManager getNetworkManager() {
		return networkManager;
	}

	/**
	 * @param networkManager the networkManager to set
	 */
	public void setNetworkManager(NetworkManager networkManager) {
		this.networkManager = networkManager;
	}

	/**
	 * @return the abstractSystemObject
	 */
	public AbstractSystemObject getAbstractSystemObject() {
		return abstractSystemObject;
	}

	/**
	 * @param abstractSystemObject the abstractSystemObject to set
	 */
	public void setAbstractSystemObject(AbstractSystemObject abstractSystemObject) {
		this.abstractSystemObject = abstractSystemObject;
	}

}

