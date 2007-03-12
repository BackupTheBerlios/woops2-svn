package network;

public class Interpretor {

	private static Interpretor interpretor;
	
	/**
	 * 
	 *
	 */
	private Interpretor() {
		// None.
	}

	/**
	 * To obliged the unicity of the Interpretor instance.
	 * 
	 * @return
	 */
	public static Interpretor getInstance() {
		if (interpretor == null)
			interpretor = new Interpretor();
		return interpretor;
	}

	/**
	 * Receive the _buffer from the System and send 
	 * good data to the NetworkManager.
	 */
	public void createBusStop(String _n) {
		String tmp = "@createBusStop:" + _n + ";";
		NetworkManager.getInstance().sendMessage(tmp);
	}
	
}
