package isimarket.servants.actiontypeservant.impl;

import isimarket.db.dao.ActionTypeDao;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.actiontypeservant._ActionTypeServantImplBase;

import java.util.List;

public class ActionTypeServantImpl extends _ActionTypeServantImplBase{

	private static final long serialVersionUID = 1L;
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	public ActionType getActionType(String _actionTypeCode) {
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		return actionType;
	}

	public ActionType[] getActionTypeList() {
		List<ActionType> actionTypelist = this.actionTypeDao.getAll();
		ActionType[] actionTypeArray = new ActionType[actionTypelist.size()];
		return actionTypelist.toArray(actionTypeArray);
	}

	public Event[] getEvents(String _actionTypeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createNewActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		this.actionTypeDao.insert(code, label, introductionDate, introductionPrice, quantity, currentPrice);
	}

	public void updateActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float newPrice) {
		this.actionTypeDao.updateCurrentPrice(code, newPrice);
		this.actionTypeDao.updateQuantity(code, quantity);
		
	}

}
