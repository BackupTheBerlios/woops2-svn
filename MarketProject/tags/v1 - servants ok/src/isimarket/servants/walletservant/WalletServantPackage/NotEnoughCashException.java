package isimarket.servants.walletservant.WalletServantPackage;


/**
* isimarket/model/servants/walletservant/WalletServantPackage/NotEnoughCashException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 21 avril 2008 12 h 28 CEST
*/

public final class NotEnoughCashException extends org.omg.CORBA.UserException
{
  public String reason = null;

  public NotEnoughCashException ()
  {
    super(NotEnoughCashExceptionHelper.id());
  } // ctor

  public NotEnoughCashException (String _reason)
  {
    super(NotEnoughCashExceptionHelper.id());
    reason = _reason;
  } // ctor


  public NotEnoughCashException (String $reason, String _reason)
  {
    super(NotEnoughCashExceptionHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class NotEnoughCashException