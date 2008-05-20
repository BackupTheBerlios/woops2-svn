package isimarket.ws.server;

import isimarket.client.CorbaClient;
import isimarket.model.ActionType;
import isimarket.servants.actiontypeservant.ActionTypeServant;

public class WSServer {
	
	CorbaClient corbaClient = new CorbaClient();
	
	// a renommer en .jws et a mettre sur axis

	public void updateActionTypeRate(ActionType actionType, float rate) {
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		at.updateActionTypeCurrentPrice(actionType.code, actionType.currentPrice + rate);
	}
	
	public void createHistoryLine() {
		
	}
	
	public void createActionType() {
		
	}
	
	public ActionType[] getActionTypeList(Object[] objs) {
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		return at.getActionTypeList();
	}
}
