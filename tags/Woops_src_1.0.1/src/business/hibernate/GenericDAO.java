package business.hibernate;

import java.io.Serializable;

import business.hibernate.exception.PersistanceException;

/**
 * @author Nicolas Ricard
 * 
 */
public interface GenericDAO {
	
	public void save(PersistentObject objet) throws PersistanceException ;
	public void update(PersistentObject objet) throws PersistanceException;	
	public PersistentObject getForUpdate(Class classe, Serializable id) throws PersistanceException;		
	public void delete(PersistentObject objet) throws PersistanceException ;
	
}