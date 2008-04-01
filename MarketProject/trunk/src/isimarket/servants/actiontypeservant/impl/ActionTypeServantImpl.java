package isimarket.servants.actiontypeservant.impl;

import isimarket.db.dao.ActionTypeDao;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.actiontypeservant._ActionTypeServantImplBase;

import java.util.List;

public class ActionTypeServantImpl extends _ActionTypeServantImplBase{

	private static final long serialVersionUID = 1L;
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	public void createNewActionType(ActionType _newActionType) {
		this.actionTypeDao.insert(_newActionType);
	}

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

	public void updateActionType(ActionType _newActionType) {
		this.actionTypeDao.updateCurrentPrice(_newActionType);
		this.actionTypeDao.updateQuantity(_newActionType);
	}

	public void createNewActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		// TODO Auto-generated method stub
		
	}

	public void updateActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		// TODO Auto-generated method stub
		
	}

}
