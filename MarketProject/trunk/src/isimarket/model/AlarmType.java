package isimarket.model;


/**
* isimarket/model/AlarmType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 24 mars 2008 11 h 43 CET
*/

public final class AlarmType implements org.omg.CORBA.portable.IDLEntity
{
  public int alarmType_oid = (int)0;
  public String label = null;

  public AlarmType ()
  {
  } // ctor

  public AlarmType (int _alarmType_oid, String _label)
  {
    alarmType_oid = _alarmType_oid;
    label = _label;
  } // ctor

} // class AlarmType
