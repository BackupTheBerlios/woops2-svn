package market.model;


/**
* market/model/AlarmListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 23 mars 2008 11 h 38 CET
*/

public final class AlarmListHolder implements org.omg.CORBA.portable.Streamable
{
  public market.model.Alarm value[] = null;

  public AlarmListHolder ()
  {
  }

  public AlarmListHolder (market.model.Alarm[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = market.model.AlarmListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    market.model.AlarmListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return market.model.AlarmListHelper.type ();
  }

}
