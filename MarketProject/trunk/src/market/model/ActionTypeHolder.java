package market.model;

/**
* market/model/ActionTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* dimanche 23 mars 2008 11 h 38 CET
*/

public final class ActionTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public market.model.ActionType value = null;

  public ActionTypeHolder ()
  {
  }

  public ActionTypeHolder (market.model.ActionType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = market.model.ActionTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    market.model.ActionTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return market.model.ActionTypeHelper.type ();
  }

}
