package business.hibernate;

import java.util.Date;


public class HistorizedObject implements PersistentObject, Cloneable{
	private static final long serialVersionUID = -4486169491171535845L; /** Generated Serial ID */
	protected Integer userCreation;
    protected Date dateCreation;
    protected Integer userModification;    
    protected Date dateModification;
    
    
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Date getDateModification() {
        return dateModification;
    }
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Integer getUserCreation() {
		return userCreation;
	}
	public void setUserCreation(Integer userCreation) {
		this.userCreation = userCreation;
	}
	public Integer getUserModification() {
		return userModification;
	}
	public void setUserModification(Integer userModification) {
		this.userModification = userModification;
	}
	public void setId(Object id) {}   
    public Object getId() {return null;}


    /**
     * surcharge de la methode clone pour copier les objets
     */
    public Object clone()  {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
        }
        
        return o;
    }    
    
}
