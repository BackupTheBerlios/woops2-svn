package isimarket.servants.walletservant.impl;

import isimarket.db.dao.ActionDao;
import isimarket.db.dao.ActionTypeDao;
import isimarket.db.dao.WalletDao;
import isimarket.model.Action;
import isimarket.model.ActionType;
import isimarket.model.Wallet;
import isimarket.servants.walletservant._WalletServantImplBase;
import isimarket.servants.walletservant.WalletServantPackage.BadQuantityException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException;
import isimarket.server.ServerConstants;

public class WalletServantImpl extends _WalletServantImplBase {
	
	private static final long serialVersionUID = 1L;

	protected ActionDao actionDao = new ActionDao();
	
	protected WalletDao walletDao = new WalletDao();
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	public Action buyAction(int _walletId, String _actionTypeCode, int _quantity) throws BadQuantityException,NotEnoughCashException {
		
		Wallet wallet = this.walletDao.get(_walletId);
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		
		// verifie que la quantite souhaitee est disponible
		if (actionType.quantity < _quantity) throw new BadQuantityException("Nombre d'actions disponibles insuffisant");
		
		float montantTransaction = _quantity * actionType.currentPrice;
		// verifie qu'il y a assez d'argent sur le compte pour effectuer la transaction
		if (wallet.cash < montantTransaction) throw new NotEnoughCashException("Solde de portefeuille insuffisant");
		
		Action action = new Action();
		action.actiontype = actionType;
		action.buyDate = ServerConstants.now();
		action.buyPrice = actionType.currentPrice;
		action.quantity = _quantity;
		action.wallet = wallet;
		
		this.actionDao.insert(action);
		this.actionTypeDao.updateQuantity(actionType.code, actionType.quantity - _quantity);
		this.walletDao.updateCash(wallet.walletId, wallet.cash - montantTransaction);
		
		return this.actionDao.getLastInsertedAction();
	}

	/**
	 * 
	 */
	public Action getAction(int _actionId) {
		return this.actionDao.get(_actionId);
	}

	/**
	 * 
	 */
	public Action[] getActionList(int _walletId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public Wallet authentication(String _operatorLogin, String _password) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public void sellAction(int _walletActionId, String _actionTypeCode, int _actionId,
			int _quantity) {
		// TODO Auto-generated method stub
		
	}

}
