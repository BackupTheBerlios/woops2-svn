package isimarket.servants.walletservant.WalletServantPackage;


/**
* isimarket/model/servants/walletservant/WalletServantPackage/WrongPasswordException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* samedi 19 avril 2008 19 h 16 CEST
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
