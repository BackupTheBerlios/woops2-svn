package isimarket.ws.server;

import isimarket.client.CorbaClient;
import isimarket.model.ActionType;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.eventservant.EventServant;
import isimarket.server.ServerConstants;

public class WSServer {

	CorbaClient corbaClient = new CorbaClient();

	public void updateActionTypeRate(ActionType actionType, float rate) {
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		at.updateActionTypeCurrentPrice(actionType.code,
				actionType.currentPrice + rate);
	}

	public void createHistoryLine(ActionType actionType) {
		EventServant es = corbaClient.getEventServantRef();
		es.createEvent(ServerConstants.now(), actionType.currentPrice,
				actionType.code);
	}

	public void createActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		at.createActionType(code, label, introductionDate, introductionPrice, quantity, currentPrice);
	}

	public ActionType[] getActionTypeList(Object[] objs) {
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		return at.getActionTypeList();
	}
}
