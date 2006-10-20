package business.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.GenericJDBCException;
import view.PresentationConstantes;
import business.BusinessConstantes;
import business.activity.sequence.ActivitySequence;
import business.activity.sequence.ActivitySequenceManager;
import business.activity.sequencetype.ActivitySequenceType;
import business.event.Event;
import business.event.EventManager;
import business.hibernate.HibernateSessionFactory;
import business.hibernate.HistorizedObject;
import business.hibernate.PersistentObject;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;
import business.user.User;



public class ActivityManager extends PersistentObjectManager {
	
	/** Instance permettant d'assurer la persistance d'une activite */
	private ActivityDAO activityDAO = new ActivityDAO();
	
	/** Instance privee de la classe */
	private static ActivityManager activityManager;

	

	/**
	 * Implementation du pattern Singleton : constructeur prive
	 */
	private ActivityManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return ActivityManager : instance de la classe
	 */
	public static ActivityManager getInstance() {
		if (activityManager == null) {
			synchronized (ActivityManager.class) {
				activityManager = new ActivityManager();
			}
		}
		return activityManager;
	}

	/**
	 * Recuperation d'une activite
	 * @param activityId : identififiant de l'activite 
	 * @return : activite correspondante
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Activity getActivityById(Integer activityId)
			throws PersistanceException {
		return activityDAO.getActivityById(activityId);
	}
	
	/**
	 * Recuperation des activites d'une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites correspondant au critere de recherche
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByBDE(Integer bdeId) throws PersistanceException {
		return activityDAO.getAllActivitiesByBDE(bdeId);
	}
	
	/**
	 * Recuperation des activites libres sur une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : liste des activites 
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getFreeActivities(Integer bdeId) throws PersistanceException {
		return activityDAO.getFreeActivities(bdeId);
	}
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @return : Liste des activites restantes du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Collection getAllActivitiesByUser(Integer userId)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserWithStates(userId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites restant à realiser pour lesquelles le participant a la responsabilite sur une entité donnéé
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites du particpant sur l'entité
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByUserByBDE(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites restant à realiser pour lesquelles le participant a la responsabilite sur une entité donnéé
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param session : session permettant d'executer la requete
	 * @return : Liste des activites du particpant sur l'entité
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByUserByBDE(Integer userId, Integer bdeId, Session session)
			throws PersistanceException {
		String[] states = new String[3];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		states[2] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states, session);
		return list;
	}
	
	/**
	 * Recuperation toutes les activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites restantes du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getRemainingActivitiesByUserByBDE(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[2];
		states[0] = BusinessConstantes.ACTIVITY_STATE_CREATED;
		states[1] = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	
	/**
	 * Recuperation des activites que le participant a terminees
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @return : historique des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitiesHistoryByUser(Integer userId, Integer bdeId)
			throws PersistanceException {
		String[] states = new String[1];
		states[0] = BusinessConstantes.ACTIVITY_STATE_FINISHED;
		Collection list = activityDAO.getActivitiesByUserByBDEWithStates(userId, bdeId, states);
		return list;
	}
	
	/**
	 * Recuperation des activites pouvant etre predecesseurs de l'activite passee en parametre
	 * @param activityId : l'activite dont on veut connaitre des dependances possibles
	 * @param bdeId : le projet de l'activité
	 * @return : liste des activites dont peut dependre l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPossibleActivityPredecessors(Integer activityId,Integer bdeId)
			throws PersistanceException {
		Collection list = activityDAO.getPossiblePredecessors(activityId,bdeId);
		return list;
	}
	
	/**
	 * Recuperation des predecesseurs d'une activite
	 * @param activityId : l'activite dont on veut connaitre ses predecesseurs
	 * @return : liste des activite dont depend l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPredecessors(Integer activityId) 
			throws PersistanceException {
		Collection listActivitySequences = activityDAO.getActivitySequencesPredecessors(activityId);
		
		Collection listPredecessors = new ArrayList();
		Iterator iter = listActivitySequences.iterator();
		
		while(iter.hasNext())
		{
			listPredecessors.add(((ActivitySequence)iter.next()).getPredecessor());
		}
		
		return listPredecessors;
	}
	
	/**
	 * Recuperation des successeurs d'une activite
	 * @param activityId : l'activite dont on veut connaitre ses successeurs
	 * @return : liste des activites dont depend l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getSuccessors(Integer activityId) 
			throws PersistanceException {
		Collection listActivitySequences = activityDAO.getActivitySequencesSuccessors(activityId);
		
		Collection listSuccessor = new ArrayList();
		Iterator iter = listActivitySequences.iterator();
		
		while(iter.hasNext())
		{
			listSuccessor.add(((ActivitySequence)iter.next()).getSuccessor());
		}
		
		return listSuccessor;
	}
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre st successeurs
	 * @param activityId : l'activite dont on veut connaitre ses dependances entrantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est successeur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivitySequencesPredecessors(activityId);
		return list;
	}
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @param activityId : l'activite dont on veut connaitre ses dependances sortantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId) 
			throws PersistanceException {
		Collection list = activityDAO.getActivitySequencesSuccessors(activityId);
		return list;
	}

	
	/**
	 * Recuperation d'une activite avec ses dependances
	 * @param activityId : identifiant de l'activite
	 * @return activite
	 * @throws PersistanceException
	 */
	public Activity getActivityWithDependances(Integer activityId) throws PersistanceException {
		Activity activity = activityDAO.getActivityById(activityId);
		activity.setListPredecessors(ActivityManager.getInstance().getActivitySequencesPredecessors(activityId));
		return activity;
	}
	
	/**
	 * Verification de l'etat du predecesseur d'une activite
	 * @param actSeq : dependance qui indique le predecesseur d'une activite
	 * @param linkType1 : type de lien a verifier
	 * @param linkType2 : type de lien a verifier
	 * @return retourne Vrai si l'etat du predecessor est le bon, faux sinon
	 */
	private boolean verifPredecessorState(ActivitySequence actSeq, String linkType1, String linkType2) {
		boolean result = true;
		
		// Si il n'y a pas de lien Finish To ...
		if (actSeq.getLinkType().getName().equals(linkType1)) {
			// Et verifier si l'activite predecessor est au bon etat
			if (!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED))
				result = false;
		}
				
		// Ou de lien Start To ...
	    if (actSeq.getLinkType().getName().equals(linkType2)) {
	    	// Et verifier si l'activite predecessor est au bon etat
	    	if ((!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS))&&
	    	     (!actSeq.getPredecessor().getState().equals(BusinessConstantes.ACTIVITY_STATE_FINISHED)))
				result = false;
	    }
	    
	    return result;
	}
	
		
	/**
	 * Permet de verifier si le participant peut changer une activite
	 * @param activity activite a verifier
	 * @return retourne l'etat dans lequel on peut changer l'activite, null si elle ne peut pas changer d'etat
	 * @throws PersistanceException
	 */
	public String verifChangeStateActivity(Activity activity) throws PersistanceException {
		boolean result = true;
		String state = null;
		Iterator iter;
		String activityState = activity.getState().getName();
		
		// Recuperation de la liste des ActivitySequence de l'activite
		Collection activitySeq = ActivityManager.getInstance().getActivitySequencesPredecessors((Integer) activity.getId());
		
		// Si l'activite est Created
		// Et qu'elle veut pouvoir commencer
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_CREATED)) {
			iter = activitySeq.iterator();
			
			// Il faut verifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_START,BusinessConstantes.LINK_TYPE_START_TO_START);
		
			if (result==true) {
				state  = BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS;
			}
		}
	
		// Si l'activite est In Progress
		// Et qu'elle veut pouvoir finir
		if (activityState.equals(BusinessConstantes.ACTIVITY_STATE_IN_PROGRESS)) {
			iter = activitySeq.iterator();
			
			// Il faut verifier dans ses predecesseurs
			while ((iter.hasNext())&&(result==true))
				result = verifPredecessorState((ActivitySequence)iter.next(),BusinessConstantes.LINK_TYPE_FINISH_TO_FINISH,BusinessConstantes.LINK_TYPE_START_TO_FINISH);
		
			if (result==true) {
				state  = BusinessConstantes.ACTIVITY_STATE_FINISHED;
			}
		}
		
		return state;
	}
	
	/**
	 * Recupere les activites qui ne sont pas liees au projet
	 * On recupere toutes les activites du projet et on verifie avec la liste passee
	 * en parametre celles qui n'existent pas dans le projet 
	 * @param listActivities : liste des activites
	 * @throws PersistanceException 
	 */
	public Collection getNewActivities(Integer bdeId, Collection listActivities) throws PersistanceException {
		listActivities.removeAll(getAllActivitiesByBDE(bdeId));
		return listActivities;
	}
	   
    
    
	
    /***************************
	*  Creation d'une session  *
	***************************/
	
	/**
	 * Sauvegarde les dependances d'une activite
	 * @param activityId : identifiant de l'activite
	 * @param oldDependancesKeys : dependances avant la modification
	 * @param newDependancesKeys : dependances apres la modification
	 * @throws PersistanceException
	 * @throws ForeignKeyException 
	 * @throws DoublonException 
	 */
	public void saveActivityDependances(Integer activityId, Collection oldDependancesKeysList, Collection newDependancesKeysList, User user) throws PersistanceException, ForeignKeyException, DoublonException {
		Session session = null ;
		Transaction transaction = null;
		
		Collection dependancesToAddList = new ArrayList(newDependancesKeysList);
		dependancesToAddList.removeAll(oldDependancesKeysList);
		
		Collection dependancesToRemoveList = new ArrayList(oldDependancesKeysList);
		dependancesToRemoveList.removeAll(newDependancesKeysList);
		
		ActivitySequenceManager activitySequenceManager = ActivitySequenceManager.getInstance();
	
		Activity successor = new Activity();
		successor.setId(activityId);
		Activity predecessor = new Activity();
		ActivitySequenceType linkType = new ActivitySequenceType();
		
		try {
			session = HibernateSessionFactory.currentSession();
			transaction = session.beginTransaction();
		
			// Suppression des dépendances
			Iterator dependancesToRemoveIter = dependancesToRemoveList.iterator();
			while(dependancesToRemoveIter.hasNext())
			{
				predecessor.setId(dependancesToRemoveIter.next());
				activitySequenceManager.removeActivityDependances(predecessor, successor, session);
			}
			
			
			// Ajout de dependances
			Iterator dependancesToAddIter = dependancesToAddList.iterator();
			while(dependancesToAddIter.hasNext())
			{
				predecessor.setId(dependancesToAddIter.next());
				ActivitySequence newActivitySequence = new ActivitySequence();
				newActivitySequence.setPredecessor(predecessor);
				newActivitySequence.setSuccessor(successor);
				
				// si le precedesseur est onGoing, on met par défaut le linkType à 3
				if ((activityManager.getActivityById((Integer) predecessor.getId(), session)).getOnGoing().equals(PresentationConstantes.YES))
					linkType.setId(new Integer(3));
				else /* Sinon, par defaut, le type des dependances sont finsihToStart */
					linkType.setId(new Integer(1));
				
				newActivitySequence.setLinkType(linkType);
				activitySequenceManager.insert(newActivitySequence, user, session);
			}
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
	
	
	/**
	 * Suppression d'une activite avec ses dependances
	 * @param activity : activite a supprimer
	 * @throws PersistanceException
	 * @throws ForeignKeyException
	 */
    public void deleteLinksFromActivity(Activity activity) throws PersistanceException, ForeignKeyException {
    	Session session = null ;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.currentSession();
            transaction = session.beginTransaction();

            this.deleteLinks(activity, session);
            
			transaction.commit();
        } catch (GenericJDBCException se) {
            rollback(transaction);
            if (se.getErrorCode()==2292)
				throw new ForeignKeyException(se.getMessage());
			throw new PersistanceException(se.getMessage(),se);
		} catch (ForeignKeyException fe) {
			rollback(transaction);
			throw new ForeignKeyException(fe.getMessage(),fe); 
		} catch (Exception e) {
            rollback(transaction);
            throw new PersistanceException(e.getMessage(),e);
		}
    }
    
	
	public Integer insertActivityWithEvent(Activity act, Event evt) throws PersistanceException, DoublonException {
		Session session = null ;
		Transaction transaction = null;
		Integer activityId = null;
		int flag=0;
		
		try {
			session = getSession();
			transaction = session.beginTransaction();
			
			// insertion de l'activit?
			activityId = (Integer)activityDAO.insert(act,session);
			flag++;
			
			// MAJ de l'activit? de l'event
			act.setId(activityId);
			evt.setActivity(act);
			
			// insertion de l'evenement
			EventManager.getInstance().insert(evt,session);
			
			transaction.commit();
		} catch (PersistanceException e) {
			try {
				transaction.rollback();
			} catch (HibernateException e1) {
				throw new PersistanceException(e.getMessage(),e);
			}
			throw new PersistanceException(e.getMessage(),e);
		} catch (DoublonException e) {
			try {
				transaction.rollback();
			} catch (HibernateException e1) {
				throw new PersistanceException(e.getMessage(),e);
			}
			if(flag==0)
				e.setAppMessage("activity");
			else if (flag==1) {
				e.setAppMessage("event");
			}
			throw new DoublonException(e.getAppMessage(),e);
		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (HibernateException e1) {
				throw new PersistanceException(e.getMessage(),e);
			}
			throw new PersistanceException(e.getMessage(),e);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
		
		return activityId;
	}
	
	
	
	/*********************
	*  Session en cours  *
	**********************/
	
    
	/**
	 * Recuperation d'une activite
	 * @param activityId : identififiant de l'activite
	 * @param session : session en cours
	 * @return : activite correspondante
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Activity getActivityById(Integer activityId, Session session)
			throws PersistanceException {
		return activityDAO.getActivityById(activityId, session);
	}
	
	
	public void delete(PersistentObject activity, Session session) throws HibernateException, ForeignKeyException{
		activityDAO.delete(activity, session);
	}	
	
	
	
	/**
	 * Cette methode permet de supprimer une activite.
	 * Pour cela il faut egalement supprimer toutes les relations d'une activite avec les autres.
	 * 
	 * On supprime TOUS les liens relatifs a l'activite
	 * 
	 * @param activity : activite à supprimer (suppression en cascade)
	 * @param session : session en cours
	 * @throws PersistanceException, ForeignKeyException, HibernateException
	 */
	public void deleteLinks(Activity activity, Session  session) throws PersistanceException, ForeignKeyException, HibernateException {
		// On supprime toutes les dépendances de l'activite
		ActivitySequenceManager.getInstance().removeActivityDependances(activity, session);

		// On finit par supprimer l'activite
		delete(activity, session);
	}
	

	
	
	
	/****************************
	*  Manipulation de donnees  *
	****************************/
	
	public Serializable insert(PersistentObject objet, User user) throws PersistanceException, DoublonException {
		((HistorizedObject)objet).setUserCreation((Integer) user.getId());
		((HistorizedObject)objet).setDateCreation(new Date());
		
		return insert(objet);
	}
	
	
	public void update(PersistentObject objet, User user) throws PersistanceException, DoublonException {
		((HistorizedObject)objet).setUserModification((Integer) user.getId());
		((HistorizedObject)objet).setDateModification(new Date());
		
		update(objet);
	}
}