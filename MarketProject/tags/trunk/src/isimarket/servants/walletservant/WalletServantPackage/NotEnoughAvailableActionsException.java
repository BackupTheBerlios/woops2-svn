package isimarket.servants.walletservant.WalletServantPackage;


/**
* isimarket/model/servants/walletservant/WalletServantPackage/NotEnoughAvailableActionsException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 21 avril 2008 12 h 28 CEST
*/

public final class NotEnoughAvailableActionsException extends org.omg.CORBA.UserException
{
  public String reason = null;

  public NotEnoughAvailableActionsException ()
  {
    super(NotEnoughAvailableActionsExceptionHelper.id());
  } // ctor

  public NotEnoughAvailableActionsException (String _reason)
  {
    super(NotEnoughAvailableActionsExceptionHelper.id());
    reason = _reason;
  } // ctor


  public NotEnoughAvailableActionsException (String $reason, String _reason)
  {
    super(NotEnoughAvailableActionsExceptionHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class NotEnoughAvailableActionsException
