package isimarket.ws.server;

import isimarket.client.CorbaClient;
import isimarket.model.ActionType;
import isimarket.servants.actiontypeservant.ActionTypeServant;
import isimarket.servants.eventservant.EventServant;
import isimarket.server.ServerConstants;

public class WSServer {

	protected static CorbaClient corbaClient = new CorbaClient();

	public void updateActionTypeRate(String code, Float rate) {
		corbaClient.startClient();
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		ActionType a = at.getActionType(code);
		float sum = a.currentPrice + rate;
		if (sum < 0.0f)
			sum = a.currentPrice - rate;
		at.updateActionTypeCurrentPrice(code, sum);
	}

	public void createHistoryLine(String code) {
		corbaClient.startClient();
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		EventServant es = corbaClient.getEventServantRef();
		ActionType a = at.getActionType(code);
		es.createEvent(ServerConstants.now(), a.currentPrice,
				a.code);
	}

	public void createActionType(String code, String label,
			String introductionDate, float introductionPrice, int quantity,
			float currentPrice) {
		corbaClient.startClient();
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		at.createActionType(code, label, introductionDate, introductionPrice, quantity, currentPrice);
	}

	public String[] getActionTypeList(int nb) {
		corbaClient.startClient();
		ActionTypeServant at = corbaClient.getActionTypeServantRef();
		ActionType[] ats = at.getActionTypeList();
		String[] codes = new String[ats.length];
		for(int i = 0;i < ats.length;i++) codes[i] = ats[i].code;
		return codes;
	}
}
