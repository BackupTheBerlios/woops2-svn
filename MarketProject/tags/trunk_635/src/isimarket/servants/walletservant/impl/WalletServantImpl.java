package isimarket.servants.walletservant.impl;

import isimarket.db.dao.ActionDao;
import isimarket.db.dao.ActionTypeDao;
import isimarket.db.dao.WalletDao;
import isimarket.model.Action;
import isimarket.model.ActionType;
import isimarket.model.Wallet;
import isimarket.servants.walletservant._WalletServantImplBase;
import isimarket.server.ServerConstants;

public class WalletServantImpl extends _WalletServantImplBase{

	private static final long serialVersionUID = 1L;
	
	protected WalletDao walletDao = new WalletDao();
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();
	
	protected ActionDao actionDao = new ActionDao();

	public Action buyAction(int _walletId, String _actionTypeCode, int _quantity) {
		Wallet wallet = this.walletDao.get(_walletId);
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		
		Action action = new Action();
		action.actiontype = actionType;
		action.buyDate = ServerConstants.now();
		action.buyPrice = actionType.currentPrice;
		action.quantity = _quantity;
		
		this.actionDao.insert(action);
		
		return null;
	}

	public Action[] getAction(int _walletId, int _actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Action[] getActionList(int _walletId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Wallet getWallet(String _operatorLogin, String _password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sellAction(int _walletActionId, String _actionTypeCode,
			int _quantity) {
		// TODO Auto-generated method stub
		
	}

}
