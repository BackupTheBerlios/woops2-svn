package isimarket.servants.actiontypeservant.impl;

import isimarket.db.dao.ActionTypeDao;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.actiontypeservant._ActionTypeServantImplBase;

import java.util.List;

public class ActionTypeServantImpl extends _ActionTypeServantImplBase{

	private static final long serialVersionUID = 1L;
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	/**
	 * 
	 */
	public ActionType getActionType(String _actionTypeCode) {
		return this.actionTypeDao.get(_actionTypeCode);
	}

	/**
	 * 
	 */
	public ActionType[] getActionTypeList() {
		List<ActionType> actionTypelist = this.actionTypeDao.getAll();
		ActionType[] actionTypeArray = new ActionType[actionTypelist.size()];
		return actionTypelist.toArray(actionTypeArray);
	}

	/**
	 * 
	 */
	public Event[] getEvents(String _actionTypeCode) {
		return null;
	}

	/**
	 * 
	 */
	public void createActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		this.actionTypeDao.insert(code, label, introductionDate, introductionPrice, quantity, currentPrice);
	}

	/**
	 * 
	 */
	public void updateActionTypeQuantity(String code, int quantity) {
		this.actionTypeDao.updateQuantity(code, quantity);
	}
	
	/**
	 * 
	 */
	public void updateActionTypeCurrentPrice(String code, float newCurrentPrice) {
		this.actionTypeDao.updateCurrentPrice(code, newCurrentPrice);
	}

	@Override
	public void deleteActionType(String actionTypeCode) {
		this.actionTypeDao.delete(actionTypeCode);
	}

	public ActionTypeDao getActionTypeDao() {
		return actionTypeDao;
	}

}
