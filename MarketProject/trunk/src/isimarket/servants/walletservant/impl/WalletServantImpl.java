package isimarket.servants.walletservant.impl;

import isimarket.db.dao.ActionDao;
import isimarket.db.dao.ActionTypeDao;
import isimarket.db.dao.OperatorDao;
import isimarket.db.dao.WalletDao;
import isimarket.model.Action;
import isimarket.model.ActionType;
import isimarket.model.Operator;
import isimarket.model.Wallet;
import isimarket.servants.walletservant._WalletServantImplBase;
import isimarket.servants.walletservant.WalletServantPackage.BadQuantityException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException;
import isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException;
import isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorException;
import isimarket.servants.walletservant.WalletServantPackage.WrongPasswordException;
import isimarket.server.ServerConstants;

import java.util.List;

public class WalletServantImpl extends _WalletServantImplBase {
	
	private static final long serialVersionUID = 1L;

	protected ActionDao actionDao = new ActionDao();
	
	protected WalletDao walletDao = new WalletDao();
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();
	
	protected OperatorDao operatorDao = new OperatorDao();

	public Action buyAction(int _walletId, String _actionTypeCode, int _quantity) throws BadQuantityException,NotEnoughCashException {
		
		Wallet wallet = this.walletDao.get(_walletId);
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		
		// verifie que la quantite souhaitee est disponible
		if (actionType.quantity < _quantity) throw new BadQuantityException("Nombre d'actions disponibles insuffisant");
		
		float transaction = _quantity * actionType.currentPrice;
		// verifie qu'il y a assez d'argent sur le compte pour effectuer la transaction
		if (wallet.cash < transaction) throw new NotEnoughCashException("Solde de portefeuille insuffisant");
		
		Action action = new Action();
		action.actiontype = actionType;
		action.buyDate = ServerConstants.now();
		action.buyPrice = actionType.currentPrice;
		action.quantity = _quantity;
		action.wallet = wallet;
		
		this.actionDao.insert(action);
		this.actionTypeDao.updateQuantity(actionType.code, actionType.quantity - _quantity);
		this.walletDao.updateCash(wallet.walletId, wallet.cash - transaction);
		
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
	public Action[] getActionListFromWallet(int _walletId) {
		
		List<Action> actionslist = this.actionDao.getAllFromWallet(_walletId);
		Action[] actionArray = new Action[actionslist.size()];
		
		return actionslist.toArray(actionArray);
	}

	/**
	 * 
	 */
	public Wallet authentication(String _login, String _password) throws UnknownOperatorException,WrongPasswordException {
		
		Operator op = this.operatorDao.get(_login);
		
		if (op == null) throw new UnknownOperatorException("Operateur inconnu");
		
		if (!(op.password.equals(_password))) throw new WrongPasswordException("Mot de passe erroné");
		
		return op.wallet;
	}

	/**
	 * 
	 */
	public void sellAction(int _walletId, String _actionTypeCode, int _actionId,
			int _quantity) throws NotEnoughAvailableActionsException {
		
		Wallet wallet = this.walletDao.get(_walletId);
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		Action action = this.actionDao.get(_actionId);
		
		if (_quantity <= action.quantity) {
			action.quantity -= _quantity;
			this.actionDao.updateQuantity(action.actionId, action.quantity);
		}
		else
			throw new NotEnoughAvailableActionsException("Nombre d'actions choisi trop grand");
		
		float transaction = _quantity * actionType.currentPrice;
		
		if (action.quantity == 0) 
			this.actionDao.delete(action);
		
		this.actionTypeDao.updateQuantity(actionType.code, actionType.quantity + _quantity);
		this.walletDao.updateCash(wallet.walletId, wallet.cash + transaction);
		
	}

}
