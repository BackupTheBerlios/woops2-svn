package isimarket.model;

/**
* isimarket/model/WalletHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 30 mars 2008 16 h 50 CEST
*/

public final class WalletHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Wallet value = null;

  public WalletHolder ()
  {
  }

  public WalletHolder (isimarket.model.Wallet initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.WalletHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.WalletHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.WalletHelper.type ();
  }

}