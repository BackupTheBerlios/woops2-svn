package isimarket.servants.walletservant.WalletServantPackage;


/**
* isimarket/model/servants/walletservant/WalletServantPackage/WrongPasswordException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 20 mai 2008 17 h 50 CEST
*/

public final class WrongPasswordException extends org.omg.CORBA.UserException
{
  public String reason = null;

  public WrongPasswordException ()
  {
    super(WrongPasswordExceptionHelper.id());
  } // ctor

  public WrongPasswordException (String _reason)
  {
    super(WrongPasswordExceptionHelper.id());
    reason = _reason;
  } // ctor


  public WrongPasswordException (String $reason, String _reason)
  {
    super(WrongPasswordExceptionHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class WrongPasswordException
