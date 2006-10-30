package business.hibernate.exception;


/**
 * @author Nicolas Ricard
 * 
 */
public class DoublonException extends Exception {
	private static final long serialVersionUID = 6920039696419654314L; /** Generated Serial ID */
	private String appMessage = null;
	
	public DoublonException(String msg, Exception e){
		super(msg,e);
		appMessage=msg;
	}

	public DoublonException(String msg){
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
