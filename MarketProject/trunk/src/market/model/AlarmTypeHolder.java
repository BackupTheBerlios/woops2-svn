package market.model;

/**
* market/model/AlarmTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 23 mars 2008 11 h 38 CET
*/

public final class AlarmTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public market.model.AlarmType value = null;

  public AlarmTypeHolder ()
  {
  }

  public AlarmTypeHolder (market.model.AlarmType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = market.model.AlarmTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    market.model.AlarmTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return market.model.AlarmTypeHelper.type ();
  }

}
