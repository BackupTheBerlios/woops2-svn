package network;

public class Interpretor {

	private static Interpretor interpretor;

	/*
	 * Public Methods.
	 */

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
	 * Receive the _buffer from the NetworkManager and send 
	 * good data to the System.
	 */
	public void receiveBuffer(String _buffer) {
		// temporary !
		System.out.println("Buuffer received by the Interpretor: " + _buffer);
	}

	/**
	 * Receive the _buffer from the System and send 
	 * good data to the NetworkManager.
	 */
	public void sendBuffer(String _buffer) {
		// temporary !
		NetworkManager.getInstance().sendMessage(_buffer);
	}

	/*
	 * Private Methods.
	 */

	private Interpretor() {
		// None.
	}
}
