package isimarket.servants.alarmservant;


/**
* isimarket/model/servants/alarmservant/_AlarmServantImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* samedi 29 mars 2008 11 h 45 CET
*/

public abstract class _AlarmServantImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements isimarket.servants.alarmservant.AlarmServant, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _AlarmServantImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getAlarmTypeList", new java.lang.Integer (0));
    _methods.put ("getAlarmType", new java.lang.Integer (1));
    _methods.put ("createAlarmType", new java.lang.Integer (2));
    _methods.put ("deleteAlarmType", new java.lang.Integer (3));
    _methods.put ("createAlarm", new java.lang.Integer (4));
    _methods.put ("deleteAlarm", new java.lang.Integer (5));
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
       case 0:  // isimarket/model/servants/alarmservant/AlarmServant/getAlarmTypeList
       {
         isimarket.model.AlarmType $result[] = null;
         $result = this.getAlarmTypeList ();
         out = $rh.createReply();
         isimarket.model.AlarmTypeListHelper.write (out, $result);
         break;
       }

       case 1:  // isimarket/model/servants/alarmservant/AlarmServant/getAlarmType
       {
         int alarmTypeId = in.read_long ();
         isimarket.model.AlarmType $result = null;
         $result = this.getAlarmType (alarmTypeId);
         out = $rh.createReply();
         isimarket.model.AlarmTypeHelper.write (out, $result);
         break;
       }

       case 2:  // isimarket/model/servants/alarmservant/AlarmServant/createAlarmType
       {
         int alarmTypeId = in.read_long ();
         String label = in.read_string ();
         this.createAlarmType (alarmTypeId, label);
         out = $rh.createReply();
         break;
       }

       case 3:  // isimarket/model/servants/alarmservant/AlarmServant/deleteAlarmType
       {
         int alarmTypeId = in.read_long ();
         this.deleteAlarmType (alarmTypeId);
         out = $rh.createReply();
         break;
       }

       case 4:  // isimarket/model/servants/alarmservant/AlarmServant/createAlarm
       {
         int alarmType_id = in.read_long ();
         String actionTypeCode = in.read_string ();
         this.createAlarm (alarmType_id, actionTypeCode);
         out = $rh.createReply();
         break;
       }

       case 5:  // isimarket/model/servants/alarmservant/AlarmServant/deleteAlarm
       {
         int alarmType_id = in.read_long ();
         String actionTypeCode = in.read_string ();
         this.deleteAlarm (alarmType_id, actionTypeCode);
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
    "IDL:isimarket/model/servants/alarmservant/AlarmServant:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _AlarmServantImplBase