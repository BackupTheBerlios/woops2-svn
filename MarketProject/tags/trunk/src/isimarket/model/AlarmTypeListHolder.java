package isimarket.model;


/**
* isimarket/model/AlarmTypeListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 31 mars 2008 16 h 30 CEST
*/

public final class AlarmTypeListHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.AlarmType value[] = null;

  public AlarmTypeListHolder ()
  {
  }

  public AlarmTypeListHolder (isimarket.model.AlarmType[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.AlarmTypeListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.AlarmTypeListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.AlarmTypeListHelper.type ();
  }

}
