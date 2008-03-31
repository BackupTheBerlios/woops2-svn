package isimarket.model;


/**
* isimarket/model/WalletAction.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 30 mars 2008 16 h 50 CEST
*/

public final class WalletAction implements org.omg.CORBA.portable.IDLEntity
{
  public int wallet_action_id = (int)0;
  public float buyPrice = (float)0;
  public String buyDate = null;
  public int quantity = (int)0;
  public isimarket.model.ActionType actiontype = null;

  public WalletAction ()
  {
  } // ctor

  public WalletAction (int _wallet_action_id, float _buyPrice, String _buyDate, int _quantity, isimarket.model.ActionType _actiontype)
  {
    wallet_action_id = _wallet_action_id;
    buyPrice = _buyPrice;
    buyDate = _buyDate;
    quantity = _quantity;
    actiontype = _actiontype;
  } // ctor

} // class WalletAction
