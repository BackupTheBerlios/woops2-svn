package business.hibernate.exception;

/**
 * @author Nicolas Ricard
 * 
 */
public class PersistanceException extends Exception {
	private static final long serialVersionUID = -3363646817766999910L; /** Generated Serial ID */
	protected String message;

	public PersistanceException(String msg, Exception e){
		super(msg,e);
		this.message=msg;
	}

	public PersistanceException(String msg){
		super(msg);
		this.message=msg;
	}
	
}
