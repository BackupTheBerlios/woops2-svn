package isimarket.model;


/**
* isimarket/model/Wallet.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 18 mai 2008 17 h 48 CEST
*/

public final class Wallet implements org.omg.CORBA.portable.IDLEntity
{
  public int walletId = (int)0;
  public float cash = (float)0;

  public Wallet ()
  {
  } // ctor

  public Wallet (int _walletId, float _cash)
  {
    walletId = _walletId;
    cash = _cash;
  } // ctor

} // class Wallet
