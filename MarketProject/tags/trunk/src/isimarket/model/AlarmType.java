package isimarket.model;


/**
* isimarket/model/AlarmType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 31 mars 2008 16 h 30 CEST
*/

public final class AlarmType implements org.omg.CORBA.portable.IDLEntity
{
  public int alarmType_id = (int)0;
  public String label = null;

  public AlarmType ()
  {
  } // ctor

  public AlarmType (int _alarmType_id, String _label)
  {
    alarmType_id = _alarmType_id;
    label = _label;
  } // ctor

} // class AlarmType
