package model;

public class Information extends SystemObject {
	
	private BusStop recipient;
	
	private String message;

	/**
	 * @param _recipient
	 * @param _message
	 */
	public Information(BusStop _recipient, String _message) {
		this.recipient = _recipient;
		this.message = _message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param _message the message to set
	 */
	public void setMessage(String _message) {
		this.message = _message;
	}

	/**
	 * @return the recipient
	 */
	public BusStop getRecipient() {
		return this.recipient;
	}

	/**
	 * @param _recipient the recipient to set
	 */
	public void setRecipient(BusStop _recipient) {
		this.recipient = _recipient;
	}
	
	

}
