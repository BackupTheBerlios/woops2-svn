package isimarket.servants.actiontypeservant;

/**
* isimarket/model/servants/actiontypeservant/ActionTypeServantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 21 avril 2008 12 h 28 CEST
*/

public final class ActionTypeServantHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.servants.actiontypeservant.ActionTypeServant value = null;

  public ActionTypeServantHolder ()
  {
  }

  public ActionTypeServantHolder (isimarket.servants.actiontypeservant.ActionTypeServant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.servants.actiontypeservant.ActionTypeServantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.servants.actiontypeservant.ActionTypeServantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.servants.actiontypeservant.ActionTypeServantHelper.type ();
  }

}