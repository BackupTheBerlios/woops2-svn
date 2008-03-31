package isimarket.servants.walletservant;


/**
* isimarket/model/servants/walletservant/WalletServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* dimanche 30 mars 2008 16 h 51 CEST
*/

public interface WalletServantOperations 
{
  isimarket.model.Wallet getWallet (String operatorLogin, String password);
  isimarket.model.WalletAction[] getActionListForWallet (int walletId);
  isimarket.model.WalletAction[] getWalletAction (int walletId, int walletActionId);
  isimarket.model.WalletAction buyAction (int walletId, String actionTypeCode, int quantity);
  void sellAction (int walletActionId, String actionTypeCode, int quantity);
} // interface WalletServantOperations
