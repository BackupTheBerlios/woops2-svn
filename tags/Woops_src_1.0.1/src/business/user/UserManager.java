package business.user;

import java.util.Collection;
import java.util.Iterator;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.GenericJDBCException;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class UserManager extends PersistentObjectManager {
	
	private UserDAO dao = new UserDAO();	
	
	private static UserManager instance;
	
	/**
	 * Singleton -> Constructeur prive
	 */
	private UserManager() {}

	public static UserManager getInstance() {
		if (instance == null) {
			synchronized (UserManager.class) {
				instance = new UserManager();
			}
		}
		return instance;
	}
	
	
	/**
	 * Recuperation d'un utilisateur a partir de son login
	 * @param login : login unique pour chaque utilisateur
	 * @return utilisateur correspondant au login
	 * @throws PersistanceException
	 */
	public User getUser(String login) throws PersistanceException {
		User user = dao.get(login);		
		return user;
	}
	

	/**
	 * Cette methode controle la validite du couple login/mot de passe
	 * @param login : login
	 * @param password : mot de passe associe
	 * @return utilisateur correspondant aux criteres de recherche
	 * @throws PersistanceException
	 */
	public User isLoginValid(String login, String password) throws PersistanceException {
		User user = dao.getUser(login,password);
		
		return user;
	}	
	
	
	/**
	 * Fournit tous les participants d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : liste des participants
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getUsersByBDE(Integer bdeId) throws PersistanceException {
		return dao.getUsersByBDE(bdeId);
	}
	
	
	/**
	 * Cette méthode permet d'obtenir un participant a partir de son identification
	 * @param bdeId : identifiant 
	 * @return : participant
	 * @throws PersistanceException Indique qu'une erreur s'est déroulée au moment de la récupération des données
	 */
	public User getUserById(Integer id){
		
		User user = new User();
		
		try {
			user = dao.getUser(id);
		} catch (PersistanceException e) {
			user = null;
		}
		return user;
	}
	
	
	
	
	
    /***************************
	*  Creation d'une session  *
	***************************/
	
	
	/**
	 * Suppression d'un utilisateur
	 * @param user : utilisateur a supprimer
	 * @throws PersistanceException
	 * @throws ForeignKeyException
	 */
	public void delete(User user) throws PersistanceException, ForeignKeyException {	
		Session session = null ;
		Transaction transaction = null;
		Collection dbData = null;
		try {
			// Chercher les activites concernant de l'utilisateur a supprimer
			// Mise a jour le champs de "UserId" a null de ces activites
			dbData = ActivityManager.getInstance().getAllActivitiesByUser((Integer)user.getId());
			
			session = this.getSession();
			// Création d'une transaction pour pouvoir annuler l'ensemble des modifications en cas d'erreur
			transaction = session.beginTransaction();
			
			Iterator iter = dbData.iterator();
	    	while (iter.hasNext()) {
	    		Activity activity = (Activity) iter.next();
	    		activity.setUserId(null);
	    		ActivityManager.getInstance().update(activity, session);
	    	}
	    	// Recuperation de l'utilisateur avec la liste des projets
	    	user = (User) session.load(User.class, (Integer) user.getId());

	    	/* Suppression des affectations aux entités,
	    	l'objet user est persistant donc la suppression 
	    	se repercute sur la BD automatiquement grace a la session en cours */
	    	user.getBdes().clear();
	    	
	    	// Suppression de l'utilisateur
	    	this.delete(user, session);
			
	    	// Tout s'est bien passé, on valide la transaction
			transaction.commit();
			
	    } catch (GenericJDBCException se) {
			this.rollback(transaction);
	        if (se.getErrorCode()==2292)
	        	throw new ForeignKeyException(se.getMessage());
			throw new PersistanceException(se.getMessage(),se);
	    } catch (HibernateException he) {
	        this.rollback(transaction);
	        throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					this.closeSession(session);			
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}	
}