package business.activity.sequence;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import business.BusinessConstantes;
import business.activity.Activity;
import business.hibernate.PersistentObjectDAO;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

public class ActivitySequenceDAO extends PersistentObjectDAO {
	
	/**
	 * Recuperation des sequences d'activites d'un projet
	 * @param bdeId : identifiant du projet
	 * @return : Liste des sequences d'activites du projet
	 * @throws PersistanceException : Indique qu'une erreur s'est produite au moment de la r?cup?ration des donn?es
	 */
	public Collection getActivitySequencesByBDE(Integer bdeId) throws PersistanceException {
		//Constitution de la requete  
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq");
		query.append(" WHERE actSeq.predecessor.bdeId = "+bdeId);
		
		// Recuperation des donnees
		List listActivitySequences = executeQuery(query.toString());
		return listActivitySequences;
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
		//Constitution de la requete  
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence as actSeq");
		query.append(" WHERE actSeq.predecessor.bdeId = "+bdeId);
		
		// Recuperation des donnees
		List listActivitySequences = executeQuery(query.toString() , session);
		return listActivitySequences;
	}
	
	
	/**
	 * Suppression des dependances d'une activite
	 * @param activity : activite pour laquelle on supprime les dependances
	 * @param session : session en cours
	 * @throws HibernateException
	 * @throws ForeignKeyException
	 */
	public void removeActivityDependances(Activity activity, Session session) throws HibernateException, ForeignKeyException {
		StringBuffer query = new StringBuffer();
		query.append("FROM " + BusinessConstantes.TABLE_ACTIVITY_SEQUENCE);
		query.append(" WHERE successor = " + activity.getId());
		query.append(" OR predecessor = "+ activity.getId());
		delete(query.toString(), session);
	}
	
	
	/**
	 * Supprime la dependance entre 2 activites
	 * @param predecessor : activite predecesseur
	 * @param successor : activite successeur
	 * @param session : session en cours
	 * @throws ForeignKeyException
	 * @throws HibernateException
	 * @throws PersistanceException
	 */
	public void removeActivityDependances(Activity predecessor, Activity successor, Session session) 
	throws PersistanceException, ForeignKeyException, HibernateException {
		StringBuffer query = new StringBuffer();
		query.append("FROM ActivitySequence actSeq");
		query.append(" WHERE actSeq.successor.id = "+successor.getId().toString());
		query.append(" AND actSeq.predecessor.id = "+predecessor.getId().toString());
		delete(query.toString(), session);
	}
}