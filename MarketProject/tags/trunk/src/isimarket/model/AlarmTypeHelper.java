package isimarket.model;


/**
* isimarket/model/AlarmTypeHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from model.idl
* mardi 20 mai 2008 18 h 00 CEST
*/

abstract public class AlarmTypeHelper
{
  private static String  _id = "IDL:isimarket/model/AlarmType/AlarmType:1.0";

  public static void insert (org.omg.CORBA.Any a, isimarket.model.AlarmType that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static isimarket.model.AlarmType extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "alarmTypeId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "label",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "symbol",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (isimarket.model.AlarmTypeHelper.id (), "AlarmType", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static isimarket.model.AlarmType read (org.omg.CORBA.portable.InputStream istream)
  {
    isimarket.model.AlarmType value = new isimarket.model.AlarmType ();
    value.alarmTypeId = istream.read_long ();
    value.label = istream.read_string ();
    value.symbol = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, isimarket.model.AlarmType value)
  {
    ostream.write_long (value.alarmTypeId);
    ostream.write_string (value.label);
    ostream.write_string (value.symbol);
  }

}
