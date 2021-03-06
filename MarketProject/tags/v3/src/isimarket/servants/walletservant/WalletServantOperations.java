package isimarket.servants.walletservant;


/**
* isimarket/model/servants/walletservant/WalletServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 20 mai 2008 17 h 50 CEST
*/

public interface WalletServantOperations 
{
  isimarket.model.Wallet authentication (String login, String password) throws isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorException, isimarket.servants.walletservant.WalletServantPackage.WrongPasswordException;
  isimarket.model.Action[] getActionListFromWallet (int walletId);
  isimarket.model.Action getAction (int actionId);
  isimarket.model.Action buyAction (int walletId, String actionTypeCode, int quantity) throws isimarket.servants.walletservant.WalletServantPackage.BadQuantityException, isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException;
  void sellAction (int walletId, String actionTypeCode, int actionId, int quantity) throws isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException;
} // interface WalletServantOperations
