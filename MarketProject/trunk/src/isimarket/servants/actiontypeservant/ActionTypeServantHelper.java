package isimarket.servants.actiontypeservant;


/**
* isimarket/model/servants/actiontypeservant/ActionTypeServantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 8 avril 2008 19 h 27 CEST
*/

abstract public class ActionTypeServantHelper
{
  private static String  _id = "IDL:isimarket/model/servants/actiontypeservant/ActionTypeServant:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.servants.actiontypeservant.ActionTypeServant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.servants.actiontypeservant.ActionTypeServant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (isimarket.servants.actiontypeservant.ActionTypeServantHelper.id (), "ActionTypeServant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static isimarket.servants.actiontypeservant.ActionTypeServant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ActionTypeServantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.servants.actiontypeservant.ActionTypeServant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static isimarket.servants.actiontypeservant.ActionTypeServant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.actiontypeservant.ActionTypeServant)
      return (isimarket.servants.actiontypeservant.ActionTypeServant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.actiontypeservant._ActionTypeServantStub stub = new isimarket.servants.actiontypeservant._ActionTypeServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static isimarket.servants.actiontypeservant.ActionTypeServant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.actiontypeservant.ActionTypeServant)
      return (isimarket.servants.actiontypeservant.ActionTypeServant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.actiontypeservant._ActionTypeServantStub stub = new isimarket.servants.actiontypeservant._ActionTypeServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
