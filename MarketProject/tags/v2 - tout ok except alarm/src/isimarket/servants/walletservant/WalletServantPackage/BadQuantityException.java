package isimarket.servants.walletservant.WalletServantPackage;


/**
* isimarket/model/servants/walletservant/WalletServantPackage/BadQuantityException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* dimanche 4 mai 2008 19 h 59 CEST
*/

public final class BadQuantityException extends org.omg.CORBA.UserException
{
  public String reason = null;

  public BadQuantityException ()
  {
    super(BadQuantityExceptionHelper.id());
  } // ctor

  public BadQuantityException (String _reason)
  {
    super(BadQuantityExceptionHelper.id());
    reason = _reason;
  } // ctor


  public BadQuantityException (String $reason, String _reason)
  {
    super(BadQuantityExceptionHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class BadQuantityException
