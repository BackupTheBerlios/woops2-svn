package network;

import model.SystemObject;

public class DataSystemObject extends AbstractSystemObject{
	
	private SystemObject systemObject;
	
	public DataSystemObject(String _buffer) {
		super(_buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void applyToSystem() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the systemObject
	 */
	public SystemObject getSystemObject() {
		return this.systemObject;
	}

	/**
	 * @param systemObject the systemObject to set
	 */
	public void setSystemObject(SystemObject _systemObject) {
		this.systemObject = _systemObject;
	}	
}
