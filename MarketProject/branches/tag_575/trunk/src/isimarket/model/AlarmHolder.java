package isimarket.model;

/**
* isimarket/model/AlarmHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 30 mars 2008 16 h 50 CEST
*/

public final class AlarmHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Alarm value = null;

  public AlarmHolder ()
  {
  }

  public AlarmHolder (isimarket.model.Alarm initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.AlarmHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.AlarmHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.AlarmHelper.type ();
  }

}