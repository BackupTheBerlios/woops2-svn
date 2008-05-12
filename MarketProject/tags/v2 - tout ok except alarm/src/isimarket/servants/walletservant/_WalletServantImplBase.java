package isimarket.servants.walletservant;


/**
* isimarket/model/servants/walletservant/_WalletServantImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* dimanche 4 mai 2008 19 h 59 CEST
*/

public abstract class _WalletServantImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements isimarket.servants.walletservant.WalletServant, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _WalletServantImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("authentication", new java.lang.Integer (0));
    _methods.put ("getActionListFromWallet", new java.lang.Integer (1));
    _methods.put ("getAction", new java.lang.Integer (2));
    _methods.put ("buyAction", new java.lang.Integer (3));
    _methods.put ("sellAction", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // isimarket/model/servants/walletservant/WalletServant/authentication
       {
         try {
           String login = in.read_string ();
           String password = in.read_string ();
           isimarket.model.Wallet $result = null;
           $result = this.authentication (login, password);
           out = $rh.createReply();
           isimarket.model.WalletHelper.write (out, $result);
         } catch (isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorException $ex) {
           out = $rh.createExceptionReply ();
           isimarket.servants.walletservant.WalletServantPackage.UnknownOperatorExceptionHelper.write (out, $ex);
         } catch (isimarket.servants.walletservant.WalletServantPackage.WrongPasswordException $ex) {
           out = $rh.createExceptionReply ();
           isimarket.servants.walletservant.WalletServantPackage.WrongPasswordExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // isimarket/model/servants/walletservant/WalletServant/getActionListFromWallet
       {
         int walletId = in.read_long ();
         isimarket.model.Action $result[] = null;
         $result = this.getActionListFromWallet (walletId);
         out = $rh.createReply();
         isimarket.model.ActionListHelper.write (out, $result);
         break;
       }

       case 2:  // isimarket/model/servants/walletservant/WalletServant/getAction
       {
         int actionId = in.read_long ();
         isimarket.model.Action $result = null;
         $result = this.getAction (actionId);
         out = $rh.createReply();
         isimarket.model.ActionHelper.write (out, $result);
         break;
       }

       case 3:  // isimarket/model/servants/walletservant/WalletServant/buyAction
       {
         try {
           int walletId = in.read_long ();
           String actionTypeCode = in.read_string ();
           int quantity = in.read_long ();
           isimarket.model.Action $result = null;
           $result = this.buyAction (walletId, actionTypeCode, quantity);
           out = $rh.createReply();
           isimarket.model.ActionHelper.write (out, $result);
         } catch (isimarket.servants.walletservant.WalletServantPackage.BadQuantityException $ex) {
           out = $rh.createExceptionReply ();
           isimarket.servants.walletservant.WalletServantPackage.BadQuantityExceptionHelper.write (out, $ex);
         } catch (isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashException $ex) {
           out = $rh.createExceptionReply ();
           isimarket.servants.walletservant.WalletServantPackage.NotEnoughCashExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // isimarket/model/servants/walletservant/WalletServant/sellAction
       {
         try {
           int walletId = in.read_long ();
           String actionTypeCode = in.read_string ();
           int actionId = in.read_long ();
           int quantity = in.read_long ();
           this.sellAction (walletId, actionTypeCode, actionId, quantity);
           out = $rh.createReply();
         } catch (isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsException $ex) {
           out = $rh.createExceptionReply ();
           isimarket.servants.walletservant.WalletServantPackage.NotEnoughAvailableActionsExceptionHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/walletservant/WalletServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _WalletServantImplBase