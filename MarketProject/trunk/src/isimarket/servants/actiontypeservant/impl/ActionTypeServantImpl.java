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
		System.out.println("getActionType : "+_actionTypeCode);
		
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		
		System.out.println("getActionType ok");
		return actionType;
	}

	public ActionType[] getActionTypeList() {
		System.out.println("getActionTypeList ...");
		
		List<ActionType> actionTypelist = this.actionTypeDao.getAll();
		ActionType[] actionTypeArray = new ActionType[actionTypelist.size()];
		
		System.out.println("getActionTypeList ok");
		return actionTypelist.toArray(actionTypeArray);
	}

	public Event[] getEvents(String _actionTypeCode) {
		return null;
	}

	public void createActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		System.out.println("createNewActionType : "+code+""+label+" ... ");
		
		this.actionTypeDao.insert(code, label, introductionDate, introductionPrice, quantity, currentPrice);
		
		System.out.println("createNewActionType ok");
	}

	public void updateActionTypeQuantity(String code, int quantity) {
		this.actionTypeDao.updateQuantity(code, quantity);
	}
	
	public void updateActionTypeCurrentPrice(String code, float newCurrentPrice) {
		this.actionTypeDao.updateCurrentPrice(code, newCurrentPrice);
	}

}
