package isimarket.servants.walletservant.WalletServantPackage;

/**
* isimarket/model/servants/walletservant/WalletServantPackage/NotEnoughAvailableActionsExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 20 mai 2008 17 h 50 CEST
*/

public final class NotEnoughAvailableActionsExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException value = null;

  public NotEnoughAvailableActionsExceptionHolder ()
  {
  }

  public NotEnoughAvailableActionsExceptionHolder (isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsExceptionHelper.type ();
  }

}
