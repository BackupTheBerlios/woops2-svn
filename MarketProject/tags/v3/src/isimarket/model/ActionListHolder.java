package isimarket.model;


/**
* isimarket/model/ActionListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* mardi 20 mai 2008 18 h 00 CEST
*/

public final class ActionListHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Action value[] = null;

  public ActionListHolder ()
  {
  }

  public ActionListHolder (isimarket.model.Action[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.ActionListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.ActionListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.ActionListHelper.type ();
  }

}
