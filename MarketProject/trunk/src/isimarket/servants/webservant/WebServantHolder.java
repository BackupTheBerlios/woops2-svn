package isimarket.servants.webservant;

/**
* isimarket/model/servants/webservant/WebServantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 25 mars 2008 18 h 20 CET
*/

public final class WebServantHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.servants.webservant.WebServant value = null;

  public WebServantHolder ()
  {
  }

  public WebServantHolder (isimarket.servants.webservant.WebServant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.servants.webservant.WebServantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.servants.webservant.WebServantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.servants.webservant.WebServantHelper.type ();
  }

}
