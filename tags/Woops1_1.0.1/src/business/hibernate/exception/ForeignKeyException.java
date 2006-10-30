package business.hibernate.exception;

/**
 * @author Nicolas RICARD
 * 
 */
public class ForeignKeyException extends Exception {
	private static final long serialVersionUID = 2833330076327764582L; /** Generated Serial ID */
	private String appMessage = null;
	
	public ForeignKeyException(String msg, Exception e){
		super(msg,e);
		appMessage=msg;
	}

	public ForeignKeyException(String msg){
		super(msg);
		appMessage=msg;
	}
	
	public String getAppMessage() {
		return appMessage;
	}
	public void setAppMessage(String appMessage) {
		this.appMessage = appMessage;
	}
}
