package business.breakdownelement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.ConstraintViolationException;
import net.sf.hibernate.exception.GenericJDBCException;
import business.activity.Activity;
import business.activity.ActivityManager;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.state.CreatedActivityState;
import business.hibernate.HibernateSessionFactory;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;
import business.user.UserManager;

public class BreakdownElementManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une entité */
	private BreakdownElementDAO breakdownElementDAO = new BreakdownElementDAO();
	
	/** Instance privée de la classe */
	private static BreakdownElementManager breakdownElementManager;

	

	/**
	 * Implémentation du pattern Singleton : constructeur privé
	 */
	private BreakdownElementManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return BreakdownElementManager : instance de la classe
	 */
	public static BreakdownElementManager getInstance() {
		if (breakdownElementManager == null) {
			synchronized (BreakdownElementManager.class) {
				breakdownElementManager = new BreakdownElementManager();
			}
		}
		return breakdownElementManager;
	}
	
	
	/**
	 * Fournit un projet par rapport à son identifiant
	 * @param bdeId identifiant du projet
	 * @return Projet correspondant
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementById(Integer bdeId) throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementById(bdeId);
	}
	
	/**
	 * Fournit un projet par rapport à son identifiant avec les participants associés
	 * @param bdeId identifiant du projet
	 * @return Projet correspondant
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public BreakdownElement getBreakDownElementByIdWithUsers(Integer bdeId) throws PersistanceException {
		BreakdownElement bde = breakdownElementDAO.getBreakDownElementById(bdeId);
		UserManager userMngr = UserManager.getInstance();
		bde.setUsers((Set)userMngr.getUsersByBDE(bdeId));
		return bde;
	}
	
	/**
	 * Fournit tous les types d'entité
	 * @return : liste de types
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementKinds() throws PersistanceException {
		return breakdownElementDAO.getBreakDownElementKinds();
	}
	
	/**
	 * Fournit tous les projets sur lesquels le participant est affecté
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des projets
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getAllBreakDownElementsByUser(Integer userId) throws PersistanceException {
		return breakdownElementDAO.getAllBreakDownElementsByUser(userId);
	}
	
	/**
	 * Fournit tous les projets non terminés sur lesquels le participant est affecté
	 * @param userId : identifiant de l'utilisateur
	 * @return : liste des projets
	 * @throws PersistanceException Indique qu'une erreur s'est au moment de la récupération des données
	 */
	public Collection getBreakDownElementsByUser(Integer userId) throws PersistanceException {
		Collection list = breakdownElementDAO.getAllBreakDownElementsByUser(userId);
		Collection listBdes = new ArrayList();
		
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			BreakdownElement bde = (BreakdownElement) iter.next();
			if (bde.getEndDate() == null) {
				listBdes.add(bde);
			}
		}
		return listBdes;
	}
	
	
	
	
    /***************************
	*  Creation d'une session  *
	***************************/
	
	
	/**
	 * Affecte des participants à une entite
	 * @param bde : entite
	 * @return : identifiant de l'entite
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de l'affectation
	 * @throws DoublonException 
	 */
	public Serializable affectUsersToBDE(BreakdownElement bde) throws PersistanceException, DoublonException {	
		if (bde.getId() != null) {
			Session session = null;
			Transaction transaction = null;
			try {
				// Mode : update
				// Récupération des participants du projet
				Collection dbData = UserManager.getInstance().getUsersByBDE((Integer)bde.getId());
		
				session = this.getSession();
				// Création d'une transaction pour pouvoir annuler l'ensemble des modifications en cas d'erreur
				transaction = session.beginTransaction();
				
				// On conserve les participants qui ont été supprimés du projet
				// Pour effectuer la suppression les 2 formats de liste doivent être identiques
				// La méthode removeAll fait appelle à la méthode equals de User
				Collection lastUsers = new ArrayList(dbData);
				Collection newUsers = new ArrayList(bde.getUsers());
				lastUsers.removeAll(newUsers);

				// Pour chaque participant on rend ses activités libres
				Iterator it = lastUsers.iterator();
				while (it.hasNext()) {
					User user = (User) it.next();
					// Recherche les activités concernant de l'utilisateur a supprimer
					// Mise a jour le champs de "UserId" a null de ces activites
					dbData = ActivityManager.getInstance().getAllActivitiesByUserByBDE((Integer)user.getId(), (Integer)bde.getId(), session);
					Iterator iter = dbData.iterator();
					while (iter.hasNext()) {
						Activity activity = (Activity) iter.next();
						activity.setUserId(null);
						ActivityManager.getInstance().update(activity, session);
					}
			
					if (user.getDefaultBDEId().intValue() == ((Integer)bde.getId()).intValue()) {
						// Recuperation de l'utilisateur pour mettre à jour son projet par défaut
				    	user = (User) session.load(User.class, (Integer) user.getId());
						// Le défaut est mis à jour en BD
						user.setDefaultBDEId(null);
					}
				}
			
				this.update(bde, session);
				
		    	// Tout s'est bien passé, on valide la transaction
				transaction.commit();
			} catch (ConstraintViolationException cve) {
	            rollback(transaction);
	            // si erreur JDBC 1062 => doublon !
	            // on lève l'erreur adéquate
	            if (cve.getErrorCode()==1062)
					throw new DoublonException(cve.getMessage());
				throw new PersistanceException(cve.getMessage(),cve);
			} catch (HibernateException he) {
	            rollback(transaction);
				throw new PersistanceException(he.getMessage(),he);
			} finally {
				try {
					if (session!=null && session.isOpen()) 
						HibernateSessionFactory.closeSession();				
				} catch (HibernateException he) {
					throw new PersistanceException(he.getMessage(),he);
				}
			}
		} else {
			// Mode : insert
			bde.setId(this.insert(bde));
		}
		return (Integer) bde.getId();
	}
	
	
	/**
	 * Copie un projet (participants et activités) dans un autre.
	 * @param srcBde : le projet source
	 * @param destBde : le projet destination (seuls les infos primaires doivent être renseignées) 
	 * @return : identifiant du projet copié
	 * @throws PersistanceException
	 * @throws DoublonException 
	 */
	public Serializable copyBreakdownElement(Integer srcBdeId, BreakdownElement destBde) throws PersistanceException, DoublonException {
		// Affectation des utilisateurs au nouveau projet
		Set users = (Set)UserManager.getInstance().getUsersByBDE(srcBdeId);
		// On crée une collection de participant a partir des participants du projet source
		destBde.setUsers(new HashSet(users));
		destBde.setEndDate(null);
		
		Session session = null;
		Transaction transaction = null;
		try {
			// Récupération de toutes les activités du projet source
			ActivityManager actMngr = ActivityManager.getInstance();
			Collection listActivities = actMngr.getAllActivitiesByBDE(srcBdeId);
		
			session = this.getSession();
			// Création d'une transaction pour pouvoir annuler l'ensemble des modifications en cas d'erreur
			transaction = session.beginTransaction();
		
			destBde.setId((Integer)breakdownElementDAO.insert(destBde, session));
		

			// On va utiliser une map pour associer les activités sources et destination
			// pour pouvoir ensuite créer les activity sequences correspondantes
			HashMap mapActSrcDest = new HashMap();
		
			// Copie de ces activités en les mettant libres et dans l'état créée
			Iterator iterAct = listActivities.iterator();
			Activity actSrc;
			Activity actDest;
			while(iterAct.hasNext()) {
				// Copie
				actSrc = (Activity)iterAct.next();
				actDest = (Activity)actSrc.clone();
				actDest.setUserId(null);
				actDest.setState(new CreatedActivityState());
				actDest.setBdeId((Integer)destBde.getId());
			
				// Insertion de l'activite
				actDest.setId(actMngr.insert(actDest, session));
			
				// Correspondance dans la map
				mapActSrcDest.put(actSrc.getId(),actDest.getId());
			}
		
			// Récupération de toutes les dépendances du projet source
			ActivitySequenceManager actSeqMngr = ActivitySequenceManager.getInstance();
			Collection listActSeq = actSeqMngr.getActivitySequencesByBDE(srcBdeId, session);
		
			// Copie des dépendances
			Iterator iterActSeq = listActSeq.iterator();
			ActivitySequence actSeqSrc;
			ActivitySequence actSeqDest;
			Activity predecessor = new Activity();
			Activity successor = new Activity();
			while(iterActSeq.hasNext()) {
				// Copie
				actSeqSrc = (ActivitySequence)iterActSeq.next();
				actSeqDest = (ActivitySequence)actSeqSrc.clone();
			
				// Correspondances des activités source et destination
				predecessor.setId((Integer)mapActSrcDest.get(actSeqSrc.getPredecessor().getId()));
				successor.setId((Integer)mapActSrcDest.get(actSeqSrc.getSuccessor().getId()));
			
				actSeqDest.setPredecessor(predecessor);
				actSeqDest.setSuccessor(successor);
			
				// Insertion en bd des dependances
				actSeqDest.setId(actSeqMngr.insert(actSeqDest, session));
			}
			
			// Tout s'est bien passé, on valide la transaction
			transaction.commit();
		} catch (ConstraintViolationException cve) {
            rollback(transaction);
            // si erreur JDBC 1062 => doublon !
            // on lève l'erreur adéquate
            if (cve.getErrorCode()==1062)
				throw new DoublonException(cve.getMessage());
			throw new PersistanceException(cve.getMessage(),cve);
		} catch (HibernateException he) {
		    rollback(transaction);
            throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
		
		return (Serializable)destBde.getId();
	}
	
	
	/**
	 * Suppression d'un projet
	 * @param bde : identifiant du projet
	 * @throws PersistanceException
	 * @throws ForeignKeyException
	 */
	public void delete(BreakdownElement bde) throws PersistanceException, ForeignKeyException {	
		Session session = null;
		Transaction transaction = null;
		try {
			// Récupération des participants du projet
			Collection dbDataUser = UserManager.getInstance().getUsersByBDE((Integer)bde.getId());
			// Recherche les activités du projet a supprimer
			Collection dbDataActivity = ActivityManager.getInstance().getAllActivitiesByBDE((Integer)bde.getId());
			// On recupere toutes les activites precedentes
			
			
			session = this.getSession();
			// Création d'une transaction pour pouvoir annuler l'ensemble des modifications en cas d'erreur
			transaction = session.beginTransaction();
			
			// Pour chaque participant on modifie son projet par défaut si besoin est
			Iterator it = dbDataUser.iterator();
			while (it.hasNext()) {
				User user = (User) it.next();
		
				if (user.getDefaultBDEId()!= null && 
						user.getDefaultBDEId().intValue() == ((Integer)bde.getId()).intValue()) {
					// Recuperation de l'utilisateur pour mettre à jour son projet par défaut
			    	user = (User) session.load(User.class, (Integer) user.getId());
					// Le défaut est mis à jour en BD
					user.setDefaultBDEId(null);
				}
			}
			
			
			// Mise a jour le champs de "BDEId" a null de ces activites
			Iterator iter = dbDataActivity.iterator();
			while (iter.hasNext()) {
				Activity activity = (Activity) iter.next();
				// On supprime l'activite et toutes ses dependances
				ActivityManager.getInstance().deleteLinks(activity, session);
			}
			
			// Recuperation du projet avec la liste des participants
	    	bde = (BreakdownElement) session.load(BreakdownElement.class, (Integer) bde.getId());

	    	/* Suppression des affectations aux participants,
	    	l'objet bde est persistant donc la suppression 
	    	se repercute sur la BD automatiquement grace a la session en cours */
	    	bde.getUsers().clear();
	    	
	    	// Suppression de l'utilisateur
	    	this.delete(bde, session);
					
	    	// Tout s'est bien passé, on valide la transaction
			transaction.commit();
		} catch (GenericJDBCException se) {
            rollback(transaction);
            if (se.getErrorCode()==2292)
                throw new ForeignKeyException(se.getMessage());
            throw new PersistanceException(se.getMessage(),se);
        } catch (HibernateException he) {
            rollback(transaction);
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}
}
