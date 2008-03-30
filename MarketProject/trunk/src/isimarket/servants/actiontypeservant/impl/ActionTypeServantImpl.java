package isimarket.servants.actiontypeservant.impl;

import isimarket.db.dao.ActionTypeDao;
import isimarket.model.ActionType;
import isimarket.model.Event;
import isimarket.servants.actiontypeservant._ActionTypeServantImplBase;

import java.util.List;

public class ActionTypeServantImpl extends _ActionTypeServantImplBase{

	private static final long serialVersionUID = 2413025648480750091L;

	public void createNewActionType(ActionType _newActionType) {
		(new ActionTypeDao()).insert(_newActionType);
	}

	public ActionType getActionType(String _actionTypeCode) {
		ActionType actionType = (new ActionTypeDao()).get(_actionTypeCode);
		return actionType;
	}

	public ActionType[] getActionTypeList() {
		List<ActionType> actionTypelist = (new ActionTypeDao()).getAll();
		ActionType[] actionTypeArray = new ActionType[actionTypelist.size()];
		return actionTypelist.toArray(actionTypeArray);
	}

	public Event[] getEvents(String _actionTypeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateActionType(ActionType _newActionType) {
		(new ActionTypeDao()).updateCurrentPrice(_newActionType);
		(new ActionTypeDao()).updateQuantity(_newActionType);
	}

}
