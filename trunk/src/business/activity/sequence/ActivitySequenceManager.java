package business.activity.sequence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import business.activity.Activity;
import business.hibernate.HistorizedObject;
import business.hibernate.PersistentObject;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;

public class ActivitySequenceManager extends PersistentObjectManager {
	/** Instance permettant d'assurer la persistance d'une activite */
	private ActivitySequenceDAO activitySequenceDAO = new ActivitySequenceDAO();
	
	/** Instance privee de la la classe */
	private static ActivitySequenceManager activitySequenceManager;

	
	/**
	 * Implementation du pattern Singleton : constructeur priv?
	 */
	private ActivitySequenceManager() {
	}

	
	/**
	 * Fournit l'instance de la classe
	 * @return ActivityManager : instance de la classe
	 */
	public static ActivitySequenceManager getInstance() {
		if (activitySequenceManager == null) {
			synchronized (ActivitySequenceManager.class) {
				activitySequenceManager = new ActivitySequenceManager();
			}
		}
		return activitySequenceManager;
	}


	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId) throws PersistanceException {
		Collection list = activitySequenceDAO.getActivitySequencesByBDE(bdeId);
		return list;
	}
	

	
	
	
	
	/*********************
	*  Session en cours  *
	**********************/
	
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @param session : session permettant d'executer la requete
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId, Session session) throws PersistanceException {
		Collection list = activitySequenceDAO.getActivitySequencesByBDE(bdeId, session);
		return list;
	}
	
	
	/**
	 * Suppression des dependances d'une activite
	 * @param activity : activite pour laquelle on supprime les dependances
	 * @param session : session en cours
	 * @throws HibernateException
	 * @throws ForeignKeyException
	 */
	public void removeActivityDependances(Activity activity, Session session) throws HibernateException, ForeignKeyException {
		activitySequenceDAO.removeActivityDependances(activity, session);
	}
	
	
	/**
	 * Suppression d'une dependance entre deux activites
	 * @param activity : activite pour laquelle on supprime les dependances
	 * @param session : session en cours
	 * @throws HibernateException
	 * @throws ForeignKeyException
	 * @throws PersistanceException 
	 */
	public void removeActivityDependances(Activity predecessor, Activity successor, Session session) throws HibernateException, ForeignKeyException, PersistanceException {
		activitySequenceDAO.removeActivityDependances(predecessor, successor, session);
	}
	
	
	
	
	
	
	/****************************
	*  Manipulation de donnees  *
	****************************/
	
	
	public Serializable insert(PersistentObject objet, User user, Session session) throws PersistanceException, DoublonException, HibernateException {
		((HistorizedObject)objet).setUserCreation((Integer) user.getId());
		((HistorizedObject)objet).setDateCreation(new Date());
		
		return insert(objet, session);
	}
	
	public void update(PersistentObject objet, User user) throws PersistanceException, DoublonException {
		((HistorizedObject)objet).setUserModification((Integer) user.getId());
	
		((HistorizedObject)objet).setDateModification(new Date());
		
		update(objet);
	}
}