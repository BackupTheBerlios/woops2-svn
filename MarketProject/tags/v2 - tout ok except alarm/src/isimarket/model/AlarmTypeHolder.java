package isimarket.model;

/**
* isimarket/model/AlarmTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* mardi 8 avril 2008 19 h 22 CEST
*/

public final class AlarmTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.AlarmType value = null;

  public AlarmTypeHolder ()
  {
  }

  public AlarmTypeHolder (isimarket.model.AlarmType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.AlarmTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.AlarmTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.AlarmTypeHelper.type ();
  }

}
