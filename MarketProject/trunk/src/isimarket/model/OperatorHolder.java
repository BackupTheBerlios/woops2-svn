package isimarket.model;

/**
* isimarket/model/OperatorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* samedi 29 mars 2008 11 h 45 CET
*/

public final class OperatorHolder implements org.omg.CORBA.portable.Streamable
{
  public isimarket.model.Operator value = null;

  public OperatorHolder ()
  {
  }

  public OperatorHolder (isimarket.model.Operator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = isimarket.model.OperatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    isimarket.model.OperatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return isimarket.model.OperatorHelper.type ();
  }

}
