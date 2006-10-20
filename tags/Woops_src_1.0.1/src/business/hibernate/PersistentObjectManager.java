package business.hibernate;

import java.io.Serializable;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

/**
 * @author Nicolas Ricard
 * 
 */
public class PersistentObjectManager {

    private PersistentObjectDAO dao = new PersistentObjectDAO();  
    
    public Session getSession() throws HibernateException  {
        return HibernateSessionFactory.currentSession();
    }

    public void closeSession(Session session) throws HibernateException  {
        if (session!=null && session.isOpen()) 
            HibernateSessionFactory.closeSession();
    }    
    
    public Serializable insert(PersistentObject objet) throws PersistanceException,DoublonException {
        return dao.insert(objet);
    }
    
    public Serializable insert(PersistentObject objet, Session session) throws PersistanceException, DoublonException   {
        return dao.insert(objet,session);
    }    
  
    
    public void update(PersistentObject objet) throws PersistanceException, DoublonException {
        dao.update(objet);
    }

    public void update(PersistentObject objet, Session session) throws HibernateException  {
        dao.update(objet,session);
    }    
    
    public void delete(PersistentObject objet) throws PersistanceException, ForeignKeyException {
        dao.delete(objet);
    }
    
    public void delete(PersistentObject objet, Session session) throws HibernateException, ForeignKeyException {
        dao.delete(objet,session);
    }
    
    public List getList(String table) throws PersistanceException {
    	return dao.getList(table);
    }
    
    public PersistentObject get(Class classe, Serializable id) throws PersistanceException {
    	return dao.get(classe,id);
    }
    
    public boolean exist(Class classe, Serializable id) throws PersistanceException {
    	return dao.exist(classe, id);
    }
    
    /**
     * Permet de faire un rollback sur une transaction et de gerer son exception
     * @param trans
     */
    protected void rollback(Transaction trans){
        try {
            trans.rollback();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
