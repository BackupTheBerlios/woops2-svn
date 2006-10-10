/**
 * 
 */
package business.event;

import java.io.Serializable;
import java.util.Collection;

import business.hibernate.PersistentObject;
import business.hibernate.PersistentObjectManager;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.PersistanceException;

/**
 * @author Simon REGGIANI
 * Cette classe permet de g?rer la classe Event
 */
public class EventManager extends PersistentObjectManager {
	/** Instance permettant d'assurer la persistance d'un evenement */
	private EventDAO eventDAO = new EventDAO();
	
	/** Instance privee de la classe */
	private static EventManager eventManager;

	

	/**
	 * Implementation du pattern Singleton : constructeur prive
	 */
	private EventManager() {
	}

	/**
	 * Fournit l'instance de la classe
	 * @return EventManager : instance de la classe
	 */
	public static EventManager getInstance() {
		if (eventManager == null) {
			synchronized (EventManager.class) {
				eventManager = new EventManager();
			}
		}
		return eventManager;
	}
	
	public Collection getEventsByBde(Integer bdeId, boolean occured) throws PersistanceException {
		return eventDAO.getEventsByBde(bdeId,occured);
	}
	
	
	
	public Serializable insert(PersistentObject object, Event event) throws PersistanceException, DoublonException{
	
			
			return insert(object);

	}
	
	
}
