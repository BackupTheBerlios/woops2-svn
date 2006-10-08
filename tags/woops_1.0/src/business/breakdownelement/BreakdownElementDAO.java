package business.breakdownelement;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.PersistanceException;
import business.user.User;

public class BreakdownElementDAO extends PersistentObjectDAO{
	
	/**
	 * Fournit un projet par rapport à son identifiant
	 * @param bdeId identifiant du projet
	 * @return Projet correspondant
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		List res = executeQuery("FROM BreakdownElement as bde WHERE bde.id = "+bdeId);
		return (BreakdownElement)res.get(0);
	}
	
	/**
	 * Fournit tous les types de projet
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return executeQuery("FROM BreakdownElementKind as bdek");
	}
	
	/**
	 * Fournit tous les projets sur lesquels le participant est affecté
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des projets
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getAllBreakDownElementsByUser(Integer userId) throws PersistanceException {
		Session session = null ;
		Transaction transaction = null;
		User user = null;
		
		try {
			session = HibernateSessionFactory.currentSession();
		
			transaction = session.beginTransaction();

			// On récupère le participant et la liste de ses projets
			Query query = session.createQuery("SELECT u FROM User u left join fetch u.bdes WHERE u.id = :uid");
            query.setParameter("uid", userId);
            user = (User) query.uniqueResult();

	    	transaction.commit();

		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

		return user.getBdes();
	}
}
