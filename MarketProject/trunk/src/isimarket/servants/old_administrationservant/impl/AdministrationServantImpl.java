package isimarket.servants.administrationservant.impl;

import isimarket.db.dao.OperatorDao;
import isimarket.db.dao.WalletDao;
import isimarket.db.manager.TableManager;
import isimarket.model.Operator;
import isimarket.model.Wallet;
import isimarket.servants.administrationservant._AdministrationServantImplBase;

public class AdministrationServantImpl extends _AdministrationServantImplBase {

	private static final long serialVersionUID = 1L;
	
	protected OperatorDao operatorDao = new OperatorDao();
	
	protected WalletDao walletDao = new WalletDao();
	
	public void createOperator(Operator _operator) {
		this.createWallet(_operator.login, 0.0f);
		Wallet wallet = this.walletDao.getLastWallet();
		_operator.wallet = wallet;
		this.operatorDao.insert(_operator);
	}

	private void createWallet(String _operatorLogin, float _initalCash) {
		Wallet wallet = new Wallet();
		wallet.cash = _initalCash;
		this.walletDao.insert(wallet);
	}

	public void updateCash(String _operatorLogin, float _newCash) {
		Operator operator = this.operatorDao.get(_operatorLogin);
		Wallet wallet = operator.wallet;
		wallet.cash = _newCash;
		this.walletDao.updateCash(wallet);
	}

}
