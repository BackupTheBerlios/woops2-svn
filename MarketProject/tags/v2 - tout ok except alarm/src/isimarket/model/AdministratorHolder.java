package isimarket.model;

/**
* isimarket/model/AdministratorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* mardi 8 avril 2008 19 h 22 CEST
*/

public final class AdministratorHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Administrator value = null;

  public AdministratorHolder ()
  {
  }

  public AdministratorHolder (isimarket.model.Administrator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.AdministratorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.AdministratorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.AdministratorHelper.type ();
  }

}
