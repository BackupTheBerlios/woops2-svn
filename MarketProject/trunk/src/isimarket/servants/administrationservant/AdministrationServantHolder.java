package isimarket.servants.administrationservant;

/**
* isimarket/model/servants/administrationservant/AdministrationServantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 8 avril 2008 19 h 27 CEST
*/

public final class AdministrationServantHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.servants.administrationservant.AdministrationServant value = null;

  public AdministrationServantHolder ()
  {
  }

  public AdministrationServantHolder (isimarket.servants.administrationservant.AdministrationServant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.servants.administrationservant.AdministrationServantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.servants.administrationservant.AdministrationServantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.servants.administrationservant.AdministrationServantHelper.type ();
  }

}
