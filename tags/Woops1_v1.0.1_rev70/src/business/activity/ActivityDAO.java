package business.activity;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.exception.GenericJDBCException;
import business.BusinessConstantes;
import business.hibernate.PersistentObject;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class ActivityDAO extends PersistentObjectDAO {
	
	/**
	 * Recuperation d'une activite
	 * @param activityId : identififiant de l'activite 
	 * @return : activite correspondante
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donn?es
	 */
	public Activity getActivityById(Integer activityId) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId);
		return (Activity)res.get(0);
	}
	

	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param states : etats des activites
	 * @return : Liste des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitiesByUserWithStates(Integer userId, String[] states) throws PersistanceException {
		// Recuperation des donnees
		return executeQuery(this.getQuery(userId, states));
	}	
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param states : etats des activites
	 * @return : Liste des activites du particpant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitiesByUserByBDEWithStates(Integer userId, Integer bdeId, String[] states) throws PersistanceException {
		// Recuperation des donnees
		return executeQuery(this.getQuery(userId, bdeId, states));
	}
	
	
	/**
	 * Recuperation des activites d'une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : Liste des activites correspondant au critere de recherche
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getAllActivitiesByBDE(Integer bdeId) throws PersistanceException {
		return executeQuery("FROM Activity as act WHERE act.bdeId = " + bdeId);
	}
	
	/**
	 * Recuperation des activites libres sur une entite
	 * @param bdeId : identifiant de l'entite
	 * @return : liste des activites libres
	 * @throws PersistanceException 
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getFreeActivities(Integer bdeId) throws PersistanceException {
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act");
		query.append(" WHERE act.userId is null");
		query.append(" AND act.bdeId = " + bdeId);
		
		// Recuperation des donnees
		List listActivities = executeQuery(query.toString());
		return listActivities;
	}
	
	
	/**
	 * Recuperation des activites pouvant etre predecesseurs de l'activite passee en parametre
	 * @param activityId : l'activite dont on veut connaitre des dependances possibles
	 * @param bdeId : le projet de l'activite
	 * @return : liste des activites dont peut dependre l'activite passee en parametre
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getPossiblePredecessors(Integer activityId, Integer bdeId) 
			throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM Activity as act WHERE act.id <> " + activityId);
		query.append(" AND act.state.name <> '" + BusinessConstantes.ACTIVITY_STATE_FINISHED + "'");
		query.append(" AND act.bdeId = " + bdeId);
		
		return executeQuery(query.toString());
	}
	
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre st successeurs
	 * @param activityId : l'activite dont on veut connaitre ses dependances entrantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est successeur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = " + activityId);
		
		return executeQuery(query.toString());
			
	}
	

	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @param activityId : l'activite dont on veut connaitre ses dependances sortantes
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.predecessor.id = " + activityId);
		
		return executeQuery(query.toString());
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
	public Activity getActivityById(Integer activityId, Session session) throws PersistanceException {
		List res = executeQuery("FROM Activity as act WHERE act.id = "+activityId, session);
		return (Activity)res.get(0);
	}
	
	
	/**
	 * Recuperation des activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param states : etats des activites
	 * @param session : Session permettant d'executer la session
	 * @return : Liste des activites du participant
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitiesByUserByBDEWithStates(Integer userId, Integer bdeId, String[] states, Session session) throws PersistanceException {
		// Recuperation des donnees
		return executeQuery(this.getQuery(userId, bdeId, states), session);
	}
	
	
	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre st successeurs
	 * @param activityId : l'activite dont on veut connaitre ses dependances entrantes
     * @param session : session en cours
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est successeur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesPredecessors(Integer activityId, Session session) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.successor.id = " + activityId);
		
		return executeQuery(query.toString(), session);
			
	}
	

	/**
	 * Recuperation des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @param activityId : l'activite dont on veut connaitre ses dependances sortantes
     * @param session : session en cours	 
	 * @return : liste des des dependances pour lesquelles l'activite passee en parametre est predecesseur
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la recuperation des donnees
	 */
	public Collection getActivitySequencesSuccessors(Integer activityId, Session session) 
		throws PersistanceException {
		StringBuffer query = new StringBuffer();
		
		query.append("FROM ActivitySequence as actSeq "); 
		query.append("WHERE actSeq.predecessor.id = " + activityId);
		
		return executeQuery(query.toString(), session);
	}
	
	
	/**
	 * Suppression d'une activite a partir d'une requ?te.
	 * Permet de forcer la suppression meme si l'objet est utilise dans la session
	 * @param activity : activite a supprimer
	 * @param session : session en cours
	 * @throws ForeignKeyException
	 * @throws HibernateException 
	 */
	public void delete(PersistentObject activity, Session session) throws HibernateException, ForeignKeyException{
        try {
        	StringBuffer req = new StringBuffer("FROM "+ BusinessConstantes.TABLE_ACTIVITY);
            req.append(" where id = "+ (Integer)activity.getId());
            delete(req.toString(), session);
        } catch (GenericJDBCException se) {
            if (se.getErrorCode()==2292)
                throw new ForeignKeyException(se.getMessage());
            throw new HibernateException(se.getMessage(),se);
        }
	}

	

	
	
	/*********************
	*  Requetes          *
	**********************/
	
	
	/**
	 * Constitution de la requete pour recuperer les activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param bdeId : identifiant de l'entite
	 * @param states : etats des activites
	 * @return : Requete a executer
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	private String getQuery(Integer userId, Integer bdeId, String[] states) throws PersistanceException {
		// Constitution de la requete en tenant compte du participant et de l'entite passes en parametre 
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId = " + userId + " AND act.bdeId = " + bdeId);  
		
		//On precise les etats a prendre en compte
		if (states.length != 0) {
			query.append(" AND ( ");
		
			// Selection des etats a prendre en compte
			for(int i = 0; i < states.length; i++) {
				query.append("act.state.name='"+ states[i] +"'");
				if (i != states.length - 1) query.append(" OR ");
			}
		
			query.append(" )");
		}
		return query.toString();
	}
	
	/**
	 * Constitution de la requete pour recuperer les activites pour lesquelles le participant a la responsabilite
	 * @param userId : identifiant du participant
	 * @param states : etats des activites
	 * @return : Requete a executer
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	private String getQuery(Integer userId, String[] states) throws PersistanceException {
		// Constitution de la requete en tenant compte du participant et de l'entite passes en parametre 
		StringBuffer query = new StringBuffer();
		query.append("FROM Activity as act WHERE act.userId = " + userId);  
	
		// On precise les etats a prendre en compte
		if (states.length != 0) {
			query.append(" AND ( ");
	
			// Selection des etats a prendre en compte
			for(int i = 0; i < states.length; i++) {
				query.append("act.state.name='"+ states[i] +"'");
				if (i != states.length - 1) query.append(" OR ");
			}
	
			query.append(" )");
		}
		return query.toString();
	}	
}