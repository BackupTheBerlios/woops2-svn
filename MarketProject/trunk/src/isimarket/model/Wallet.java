package isimarket.model;


/**
* isimarket/model/Wallet.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 24 mars 2008 11 h 43 CET
*/

public final class Wallet implements org.omg.CORBA.portable.IDLEntity
{
  public int wallet_id = (int)0;
  public float cash = (float)0;
  public isimarket.model.Action actions[] = null;

  //public List<Action> actions = new ArrayList<Action>();
  public isimarket.model.Alarm alarms[] = null;

  public Wallet ()
  {
  } // ctor

  public Wallet (int _wallet_id, float _cash, isimarket.model.Action[] _actions, isimarket.model.Alarm[] _alarms)
  {
    wallet_id = _wallet_id;
    cash = _cash;
    actions = _actions;
    alarms = _alarms;
  } // ctor

} // class Wallet
