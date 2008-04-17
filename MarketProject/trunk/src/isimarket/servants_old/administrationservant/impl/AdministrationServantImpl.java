package isimarket.servants.administrationservant.impl;

import isimarket.db.dao.OperatorDao;
import isimarket.db.dao.WalletDao;
import isimarket.model.Operator;
import isimarket.model.Wallet;
import isimarket.servants.administrationservant._AdministrationServantImplBase;

public class AdministrationServantImpl extends _AdministrationServantImplBase {

	private static final long serialVersionUID = 1L;
	
	protected OperatorDao operatorDao = new OperatorDao();
	
	protected WalletDao walletDao = new WalletDao();

	public void createWallet(float _initalCash) {
		System.out.println("createWallet : "+_initalCash);
		
		Wallet wallet = new Wallet();
		wallet.cash = _initalCash;
		this.walletDao.insert(wallet);
		
		System.out.println("createWallet ok");
	}

	public void updateCash(String _operatorLogin, float _newCash) {
		System.out.println("updateCash : "+_operatorLogin+" "+_newCash);
		
		Operator operator = this.operatorDao.get(_operatorLogin);
		Wallet wallet = operator.wallet;
		wallet.cash = _newCash;
		this.walletDao.updateCash(wallet);
		
		System.out.println("updateCash ok");
	}

	public void createOperator(String _operatorLogin, String _password,
			float inital_Cash) {
		System.out.println("createOperator : "+_operatorLogin+" "+_password+" "+inital_Cash);
		
		this.createWallet(inital_Cash);
		Wallet wallet = this.walletDao.getLastInsertedWallet();
		Operator op = new Operator();
		op.wallet = wallet;
		op.login = _operatorLogin;
		op.password = _password;
		this.operatorDao.insert(op);
		
		System.out.println("createOperator ok");
	}

}
