package isimarket.model;

/**
* isimarket/model/ActionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* lundi 31 mars 2008 16 h 30 CEST
*/

public final class ActionHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Action value = null;

  public ActionHolder ()
  {
  }

  public ActionHolder (isimarket.model.Action initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.ActionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.ActionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.ActionHelper.type ();
  }

}
