package isimarket.servants.walletservant;

/**
* isimarket/model/servants/walletservant/WalletServantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 1 avril 2008 19 h 14 CEST
*/

public final class WalletServantHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.servants.walletservant.WalletServant value = null;

  public WalletServantHolder ()
  {
  }

  public WalletServantHolder (isimarket.servants.walletservant.WalletServant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.servants.walletservant.WalletServantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.servants.walletservant.WalletServantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.servants.walletservant.WalletServantHelper.type ();
  }

}
