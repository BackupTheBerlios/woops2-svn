package isimarket.servants.actiontypeservant;


/**
* isimarket/model/servants/actiontypeservant/_ActionTypeServantImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 20 mai 2008 17 h 50 CEST
*/

public abstract class _ActionTypeServantImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements isimarket.servants.actiontypeservant.ActionTypeServant, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _ActionTypeServantImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getActionTypeList", new java.lang.Integer (0));
    _methods.put ("getActionType", new java.lang.Integer (1));
    _methods.put ("getLastInsertedActionType", new java.lang.Integer (2));
    _methods.put ("createActionType", new java.lang.Integer (3));
    _methods.put ("updateActionTypeQuantity", new java.lang.Integer (4));
    _methods.put ("updateActionTypeCurrentPrice", new java.lang.Integer (5));
    _methods.put ("deleteActionType", new java.lang.Integer (6));
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
       case 0:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/getActionTypeList
       {
         isimarket.model.ActionType $result[] = null;
         $result = this.getActionTypeList ();
         out = $rh.createReply();
         isimarket.model.ActionTypeListHelper.write (out, $result);
         break;
       }

       case 1:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/getActionType
       {
         String actionTypeCode = in.read_string ();
         isimarket.model.ActionType $result = null;
         $result = this.getActionType (actionTypeCode);
         out = $rh.createReply();
         isimarket.model.ActionTypeHelper.write (out, $result);
         break;
       }

       case 2:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/getLastInsertedActionType
       {
         isimarket.model.ActionType $result = null;
         $result = this.getLastInsertedActionType ();
         out = $rh.createReply();
         isimarket.model.ActionTypeHelper.write (out, $result);
         break;
       }

       case 3:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/createActionType
       {
         String code = in.read_string ();
         String label = in.read_string ();
         String introductionDate = in.read_string ();
         float introductionPrice = in.read_float ();
         int quantity = in.read_long ();
         float currentPrice = in.read_float ();
         this.createActionType (code, label, introductionDate, introductionPrice, quantity, currentPrice);
         out = $rh.createReply();
         break;
       }

       case 4:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/updateActionTypeQuantity
       {
         String code = in.read_string ();
         int quantity = in.read_long ();
         this.updateActionTypeQuantity (code, quantity);
         out = $rh.createReply();
         break;
       }

       case 5:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/updateActionTypeCurrentPrice
       {
         String code = in.read_string ();
         float currentPrice = in.read_float ();
         this.updateActionTypeCurrentPrice (code, currentPrice);
         out = $rh.createReply();
         break;
       }

       case 6:  // isimarket/model/servants/actiontypeservant/ActionTypeServant/deleteActionType
       {
         String actionTypeCode = in.read_string ();
         this.deleteActionType (actionTypeCode);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:isimarket/model/servants/actiontypeservant/ActionTypeServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _ActionTypeServantImplBase
