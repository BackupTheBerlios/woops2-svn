package isimarket.model;


/**
* isimarket/model/AlarmListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* samedi 29 mars 2008 11 h 45 CET
*/

public final class AlarmListHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Alarm value[] = null;

  public AlarmListHolder ()
  {
  }

  public AlarmListHolder (isimarket.model.Alarm[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.AlarmListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.AlarmListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.AlarmListHelper.type ();
  }

}