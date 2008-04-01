package isimarket.servants.administrationservant;


/**
* isimarket/model/servants/administrationservant/AdministrationServantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 1 avril 2008 18 h 10 CEST
*/

abstract public class AdministrationServantHelper
{
  private static String  _id = "IDL:isimarket/model/servants/administrationservant/AdministrationServant:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.servants.administrationservant.AdministrationServant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.servants.administrationservant.AdministrationServant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (isimarket.servants.administrationservant.AdministrationServantHelper.id (), "AdministrationServant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static isimarket.servants.administrationservant.AdministrationServant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AdministrationServantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.servants.administrationservant.AdministrationServant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static isimarket.servants.administrationservant.AdministrationServant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.administrationservant.AdministrationServant)
      return (isimarket.servants.administrationservant.AdministrationServant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.administrationservant._AdministrationServantStub stub = new isimarket.servants.administrationservant._AdministrationServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static isimarket.servants.administrationservant.AdministrationServant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.administrationservant.AdministrationServant)
      return (isimarket.servants.administrationservant.AdministrationServant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.administrationservant._AdministrationServantStub stub = new isimarket.servants.administrationservant._AdministrationServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
