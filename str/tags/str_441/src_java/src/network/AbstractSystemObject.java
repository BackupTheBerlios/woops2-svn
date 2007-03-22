package network;

public abstract class AbstractSystemObject {

	private Interpretor interpretor;

	private String buffer;

	public AbstractSystemObject(String _buffer) {
		this.buffer = _buffer;
	}

	public abstract void applyToSystem();
	
	/**
	 * @return the buffer
	 */
	public String getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer
	 *            the buffer to set
	 */
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the interpretor
	 */
	public Interpretor getInterpretor() {
		return interpretor;
	}

	/**
	 * @param interpretor
	 *            the interpretor to set
	 */
	public void setInterpretor(Interpretor interpretor) {
		this.interpretor = interpretor;
	}

}
