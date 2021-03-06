package isimarket.servants.eventservant;


/**
* isimarket/model/servants/eventservant/_EventServantImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 20 mai 2008 17 h 50 CEST
*/

public abstract class _EventServantImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements isimarket.servants.eventservant.EventServant, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _EventServantImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createEvent", new java.lang.Integer (0));
    _methods.put ("deleteEvent", new java.lang.Integer (1));
    _methods.put ("get", new java.lang.Integer (2));
    _methods.put ("getEventsForActionType", new java.lang.Integer (3));
    _methods.put ("deleteEventsForActionType", new java.lang.Integer (4));
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
       case 0:  // isimarket/model/servants/eventservant/EventServant/createEvent
       {
         String date = in.read_string ();
         float price = in.read_float ();
         String actionTypeCode = in.read_string ();
         this.createEvent (date, price, actionTypeCode);
         out = $rh.createReply();
         break;
       }

       case 1:  // isimarket/model/servants/eventservant/EventServant/deleteEvent
       {
         int eventId = in.read_long ();
         this.deleteEvent (eventId);
         out = $rh.createReply();
         break;
       }

       case 2:  // isimarket/model/servants/eventservant/EventServant/get
       {
         int eventId = in.read_long ();
         isimarket.model.Event $result = null;
         $result = this.get (eventId);
         out = $rh.createReply();
         isimarket.model.EventHelper.write (out, $result);
         break;
       }

       case 3:  // isimarket/model/servants/eventservant/EventServant/getEventsForActionType
       {
         String actionTypeCode = in.read_string ();
         isimarket.model.Event $result[] = null;
         $result = this.getEventsForActionType (actionTypeCode);
         out = $rh.createReply();
         isimarket.model.EventListHelper.write (out, $result);
         break;
       }

       case 4:  // isimarket/model/servants/eventservant/EventServant/deleteEventsForActionType
       {
         String actionTypeCode = in.read_string ();
         this.deleteEventsForActionType (actionTypeCode);
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
    "IDL:isimarket/model/servants/eventservant/EventServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _EventServantImplBase
