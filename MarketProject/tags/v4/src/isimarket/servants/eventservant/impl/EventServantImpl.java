package isimarket.servants.eventservant.impl;

import java.util.List;

import isimarket.db.dao.ActionTypeDao;
import isimarket.db.dao.EventDao;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.eventservant._EventServantImplBase;
import isimarket.server.ServerConstants;

public class EventServantImpl extends _EventServantImplBase {

	private static final long serialVersionUID = 1L;
	
	protected EventDao eventDao = new EventDao();
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	public void createEvent(String date, float price, String actionTypeCode) {
		ActionType actionType = this.actionTypeDao.get(actionTypeCode);		
		
		Event event = new Event();
		event.date = ServerConstants.now();
		event.price = price;
		event.actiontype = actionType;
		
		this.eventDao.insert(event);
	}

	public void deleteEvent(int eventId) {
		this.eventDao.delete(eventId);
	}

	public Event get(int eventId) {
		return this.eventDao.get(eventId);
	}
	
	/**
	 * 
	 */
	public Event[] getEventsForActionType(String _actionTypeCode) {
		List<Event> eventlist = this.eventDao.getAllFromActionType(_actionTypeCode);
		Event[] eventArray = new Event[eventlist.size()];
		return eventlist.toArray(eventArray);
	}

	public void deleteEventsForActionType(String actionTypeCode) {
		List<Event> eventlist = this.eventDao.getAllFromActionType(actionTypeCode);
		for (Event e : eventlist) {
			this.deleteEvent(e.eventId);
		}
	}

}
