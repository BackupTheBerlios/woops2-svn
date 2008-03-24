package isimarket.servants.alarmservant;


/**
* isimarket/model/servants/alarmservant/AlarmServantHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* lundi 24 mars 2008 12 h 24 CET
*/

abstract public class AlarmServantHelper
{
  private static String  _id = "IDL:isimarket/model/servants/alarmservant/AlarmServant:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.servants.alarmservant.AlarmServant that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.servants.alarmservant.AlarmServant extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (isimarket.servants.alarmservant.AlarmServantHelper.id (), "AlarmServant");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static isimarket.servants.alarmservant.AlarmServant read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AlarmServantStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.servants.alarmservant.AlarmServant value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static isimarket.servants.alarmservant.AlarmServant narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.alarmservant.AlarmServant)
      return (isimarket.servants.alarmservant.AlarmServant)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.alarmservant._AlarmServantStub stub = new isimarket.servants.alarmservant._AlarmServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static isimarket.servants.alarmservant.AlarmServant unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof isimarket.servants.alarmservant.AlarmServant)
      return (isimarket.servants.alarmservant.AlarmServant)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      isimarket.servants.alarmservant._AlarmServantStub stub = new isimarket.servants.alarmservant._AlarmServantStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
